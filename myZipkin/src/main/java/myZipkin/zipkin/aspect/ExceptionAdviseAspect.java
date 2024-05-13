package myZipkin.zipkin.aspect;

import brave.Span;
import brave.Tracer;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.extern.slf4j.Slf4j;
import myEntity.entity.ResultDTO;
import myZipkin.utils.ExtraParamUtil;
import myZipkin.zipkin.ZipkinThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Aspect
@Component
@ConditionalOnProperty(
    name = {"spring.zipkin.biztag.enabled"},
    havingValue = "true"
)
@Order(10000)
public class ExceptionAdviseAspect {

    @Autowired
    private Tracer tracer;

    @Value("${appId:${app.id:null}}")
    private String appId;

    @Value("${spring.zipkin.uploadArgsAndResult.enabled:false}")
    private boolean uploadArgsAndResult;

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired(required = false)
    private HttpServletResponse response;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ExceptionAdviseAspect() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE, ToStringSerializer.instance);
        this.objectMapper.registerModule(module);
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.ControllerAdvice)")
    private void anyControllerAdviceAnnotated() {
    }

//    @Pointcut("@within(com.yingzi.bizcenter.common.annotation.YzAppExceptionAdvice)")
//    private void anyYzAppExceptionAdviceAnnotated() {
//    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestControllerAdvice)")
    private void anyRestControllerAdviceAnnotated() {
    }

//    @Pointcut("execution(@org.springframework.web.bind.annotation.ExceptionHandler com.yingzi.bizcenter.common.web.ResultDTO *.*(..))")
//    private void anyExceptionHandlerAnnotatedReturningResultDTO() {
//    }

    @Pointcut("( anyControllerAdviceAnnotated() || anyRestControllerAdviceAnnotated() )")
    private void anyExceptionHandlerAnnotatedWithinControllerAdviceAnnotatedReturningResultDTO() {
    }

    @Around("anyExceptionHandlerAnnotatedWithinControllerAdviceAnnotatedReturningResultDTO()")
    public Object anyExceptionHandlerAnnotatedWithinControllerAdviceAnnotatedReturningResultDTO(ProceedingJoinPoint pjp) throws Throwable {
        Span currentSpan = this.tracer.currentSpan();
        if (currentSpan == null) {
            return pjp.proceed();
        } else {
            currentSpan.tag("sAppId", this.appId);
            if (this.request != null) {
                Long appId = ExtraParamUtil.getExtraParam().getAppId();
                currentSpan.tag("oAppId", appId == null ? "" : "" + appId);
                currentSpan.tag("requestLength", "" + this.calculateRequestLength());
            }

            if (this.response != null) {
                this.response.addHeader("X-B3-TraceId", "" + currentSpan.context().traceIdString());
            }

            ResultDTO result = (ResultDTO) pjp.proceed();
            if (result == null) {
                currentSpan.tag("result", "null");
            } else {
                result.setTraceId(currentSpan.context().traceIdString());

                try {
                    result.setPtraceId(MDC.get("PtxId"));
                } catch (Exception var11) {
                    log.warn("get ptxId error", var11);
                }

                currentSpan.tag("resultCode", "" + result.getCode());
                if (StringUtils.equals(result.getCode(), "000000")) {
                    currentSpan.tag("message", "" + result.getMsg());
                } else {
                    currentSpan.tag("error", "" + result.getMsg());
                }
            }

            if (this.uploadArgsAndResult) {
                if (this.request != null) {
                    currentSpan.tag("queryString", "" + this.request.getQueryString());
                    currentSpan.tag("header", this.requestHeaderToJson().toJSONString());
                }

                if (result != null) {
                    String resultJson = this.objectMapper.writeValueAsString(result);
                    currentSpan.tag("resultLength", "" + resultJson.length());
                    currentSpan.tag("resultData", "" + resultJson);
                }

                try {
                    Object[] args = ZipkinThreadLocal.getControllerArguments();
                    args = args == null ? new Object[0] : args;
                    List<Object> logArgs = (List) Stream.of(args).filter((arg) -> {
                        return !(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse) && !(arg instanceof BindingResult) && !(arg instanceof MultipartFile) && !(arg instanceof MultipartFile[]);
                    }).collect(Collectors.toList());
                    currentSpan.tag("controllerArgs", "" + this.objectMapper.writeValueAsString(logArgs));
                    currentSpan.tag("args", "" + this.objectMapper.writeValueAsString(Stream.of(pjp.getArgs()).filter((x) -> {
                        return this.filterArg(x);
                    }).collect(Collectors.toList())));
                    List<Object> exObjs = (List) Stream.of(pjp.getArgs()).filter((x) -> {
                        return x instanceof Throwable;
                    }).collect(Collectors.toList());
                    if (exObjs != null && !exObjs.isEmpty()) {
                        int i = 0;

                        for (Iterator var8 = exObjs.iterator(); var8.hasNext(); ++i) {
                            Object exObj = var8.next();
                            Throwable exception = (Throwable) exObj;
                            currentSpan.tag("exception" + i, exception2Json(exception).toJSONString());
                        }
                    }
                } catch (Exception var12) {
                    log.error("error on ExceptionAdviseAspect", var12);
                }
            }

            return result;
        }
    }

    private boolean filterArg(Object arg) {
        if (arg instanceof HttpServletRequest) {
            return false;
        } else if (arg instanceof HttpServletResponse) {
            return false;
        } else if (arg instanceof Throwable) {
            return false;
        } else if (arg instanceof BindingResult) {
            return false;
        } else if (arg instanceof MultipartFile) {
            return false;
        } else {
            return !(arg instanceof MultipartFile[]);
        }
    }

    private long calculateRequestLength() {
        long n = 0L;
        n += (long) (this.request.getProtocol().length() + 1);
        n += (long) (this.request.getRequestURI().length() + 1);
        n += (long) ("HTTP/1.1".length() + 2);

        for (Enumeration<String> headerNames = this.request.getHeaderNames(); headerNames.hasMoreElements(); n += 2L) {
            String headerName = (String) headerNames.nextElement();
            String headerValue = this.request.getHeader(headerName);
            StringUtils.defaultString(headerValue);
            n += (long) headerName.length();
            n += (long) headerValue.length();
        }

        n += 4L;
        n += this.request.getContentLengthLong();
        return n;
    }

    private JSONObject requestHeaderToJson() {
        JSONObject json = new JSONObject();
        Enumeration<String> headerNames = this.request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            String headerValue = this.request.getHeader(headerName);
            StringUtils.defaultString(headerValue);
            json.put(headerName, headerValue);
        }

        return json;
    }

    private static JSONArray exception2Json(Throwable ex) {
        JSONArray list = new JSONArray();
        exception2Json(ex, list);
        return list;
    }

    private static void exception2Json(Throwable ex, JSONArray list) {
        list.add(ex.getClass().getName() + ": " + StringUtils.trimToEmpty(ex.getMessage()));
        StackTraceElement[] stackTraces = ex.getStackTrace();
        if (stackTraces != null) {
            StackTraceElement[] var3 = stackTraces;
            int var4 = stackTraces.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                StackTraceElement stackTrace = var3[var5];
                list.add("    at " + stackTrace.getClassName() + "." + stackTrace.getMethodName() + "(" + stackTrace.getFileName() + ":" + stackTrace.getLineNumber() + ")");
            }
        }

        if (ex.getCause() != null) {
            exception2Json(ex.getCause(), list);
        }
    }

}