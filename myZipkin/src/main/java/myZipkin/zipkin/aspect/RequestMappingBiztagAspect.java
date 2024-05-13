//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package myZipkin.zipkin.aspect;

import brave.Span;
import brave.Tracer;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import myEntity.entity.ResultDTO;
import myZipkin.utils.ExtraParamUtil;
import myZipkin.zipkin.ZipkinThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
@Order(10000)
public class RequestMappingBiztagAspect {

    private static final Logger log = LoggerFactory.getLogger(RequestMappingBiztagAspect.class);

    @Value("${appId:${app.id:null}}")
    private String appId;

    @Value("${spring.zipkin.uploadArgsAndResult.enabled:false}")
    private boolean uploadArgsAndResult;

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired(required = false)
    private HttpServletResponse response;

    @Autowired
    private Tracer tracer;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public RequestMappingBiztagAspect() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE, ToStringSerializer.instance);
        this.objectMapper.registerModule(module);
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    private void anyRestControllerAnnotated() {
    }

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    private void anyControllerAnnotated() {
    }

    @Pointcut("execution(@org.springframework.web.bind.annotation.RequestMapping * *.*(..))")
    private void anyRequestMappingAnnotated() {
    }

    @Pointcut("execution(@org.springframework.web.bind.annotation.GetMapping * *.*(..))")
    private void anyGetMappingAnnotated() {
    }

    @Pointcut("execution(@org.springframework.web.bind.annotation.PostMapping * *.*(..))")
    private void anyPostMappingAnnotated() {
    }

    @Pointcut("execution(@org.springframework.web.bind.annotation.DeleteMapping * *.*(..))")
    private void anyDeleteMappingAnnotated() {
    }

    @Pointcut("execution(@org.springframework.web.bind.annotation.PutMapping * *.*(..))")
    private void anyPutMappingAnnotated() {
    }

    @Pointcut("execution(@org.springframework.web.bind.annotation.PatchMapping * *.*(..))")
    private void anyPatchMappingAnnotated() {
    }

    @Pointcut("anyRequestMappingAnnotated() || anyGetMappingAnnotated() || anyPostMappingAnnotated() || anyDeleteMappingAnnotated() || anyPutMappingAnnotated() || anyPatchMappingAnnotated()")
    private void anyRequestMethod() {
    }

    @Pointcut("execution(myEntity.entity.ResultDTO *.*(..))")
    private void anyReturningResultDTO() {
    }

    @Pointcut("(anyRestControllerAnnotated() || anyControllerAnnotated()) && ( anyRequestMethod() || anyReturningResultDTO() )")
    private void anyControllerOrRestControllerRequestMethod() {
    }

    @Around("anyControllerOrRestControllerRequestMethod()")
    public Object anyControllerOrRestControllerRequestMethod(ProceedingJoinPoint pjp) throws Throwable {
        ZipkinThreadLocal.setControllerArguments(pjp.getArgs());
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

            Object result = pjp.proceed();

            try {
                if (result != null && result instanceof ResultDTO) {
                    ResultDTO resultDTO = (ResultDTO)result;
                    resultDTO.setTraceId(currentSpan.context().traceIdString());
                    resultDTO.setPtraceId(MDC.get("PtxId"));
                    currentSpan.tag("resultCode", "" + resultDTO.getCode());
                    if (StringUtils.equals(resultDTO.getCode(), "000000")) {
                        currentSpan.tag("message", "" + resultDTO.getMsg());
                    } else {
                        currentSpan.tag("error", "" + resultDTO.getMsg());
                    }
                } else {
                    currentSpan.tag("result", "" + result);
                }

                if (this.uploadArgsAndResult) {
                    List<Object> logArgs = (List)Stream.of(pjp.getArgs()).filter((arg) -> {
                        return !(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse) && !(arg instanceof BindingResult) && !(arg instanceof MultipartFile) && !(arg instanceof MultipartFile[]);
                    }).collect(Collectors.toList());
                    currentSpan.tag("args", "" + this.objectMapper.writeValueAsString(logArgs));
                    if (this.request != null) {
                        currentSpan.tag("queryString", "" + this.request.getQueryString());
                        currentSpan.tag("header", this.requestHeaderToJson().toJSONString());
                    }

                    if (result != null) {
                        String resultJson = this.objectMapper.writeValueAsString(result);
                        currentSpan.tag("resultLength", "" + resultJson.length());
                        currentSpan.tag("resultData", "" + resultJson);
                    }
                }
            } catch (Exception var6) {
                log.warn("span tag error", var6);
            }

            return result;
        }
    }

    private JSONObject requestHeaderToJson() {
        JSONObject json = new JSONObject();
        Enumeration<String> headerNames = this.request.getHeaderNames();

        while(headerNames.hasMoreElements()) {
            String headerName = (String)headerNames.nextElement();
            String headerValue = this.request.getHeader(headerName);
            json.put(headerName, headerValue);
        }

        return json;
    }

    private long calculateRequestLength() {
        long n = 0L;
        n += (long)(this.request.getProtocol().length() + 1);
        n += (long)(this.request.getRequestURI().length() + 1);
        n += (long)("HTTP/1.1".length() + 2);

        for(Enumeration<String> headerNames = this.request.getHeaderNames(); headerNames.hasMoreElements(); n += 2L) {
            String headerName = (String)headerNames.nextElement();
            String headerValue = this.request.getHeader(headerName);
            n += (long)headerName.length();
            if (headerValue != null) {
                n += (long)headerValue.length();
            }
        }

        n += 4L;
        n += this.request.getContentLengthLong();
        return n;
    }
}
