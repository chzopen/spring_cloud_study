package myZipkin.zipkin;

import brave.Span;
import brave.Span.Kind;
import brave.Tracer;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Component
public class ZipkinHelper {

    @Autowired
    private Tracer tracer;

    public ZipkinHelper() {
    }

    public Runnable wrap(Runnable runnable) {
        Span currentSpan = this.tracer.currentSpan();
        return () -> {
            Tracer.SpanInScope scope = this.tracer.withSpanInScope(currentSpan);
            Throwable throwable = null;

            try {
                Span span = this.tracer.nextSpan();
                MDC.put("X-B3-TraceId", span.context().traceIdString());
                MDC.put("X-B3-SpanId", span.context().spanIdString());
                MDC.put("X-B3-ParentSpanId", span.context().parentIdString());
                span.name("new_thread_started").kind(Kind.SERVER).tag("thread_id", Thread.currentThread().getId() + "").tag("thread_name", Thread.currentThread().getName() + "");
                span.start();

                try {
                    Tracer.SpanInScope spanInScope = this.tracer.withSpanInScope(span);
                    Throwable throwable1 = null;

                    try {
                        runnable.run();
                    } catch (Throwable throwable2) {
                        throwable1 = throwable2;
                        throw throwable2;
                    } finally {
                        if (spanInScope != null) {
                            if (throwable1 != null) {
                                try {
                                    spanInScope.close();
                                } catch (Throwable throwable3) {
                                    throwable1.addSuppressed(throwable3);
                                }
                            } else {
                                spanInScope.close();
                            }
                        }
                    }
                } catch (Error | RuntimeException throwable4) {
                    span.error(throwable4);
                    throw throwable4;
                } finally {
                    span.finish();
                }
            } catch (Throwable throwable5) {
                throwable = throwable5;
                throw throwable5;
            } finally {
                if (scope != null) {
                    if (throwable != null) {
                        try {
                            scope.close();
                        } catch (Throwable throwable6) {
                            throwable.addSuppressed(throwable6);
                        }
                    } else {
                        scope.close();
                    }
                }
            }
        };
    }

    public <T> Callable<T> wrap(Callable<T> callable) {
        Span currentSpan = this.tracer.currentSpan();
        return () -> {
            Tracer.SpanInScope scope = this.tracer.withSpanInScope(currentSpan);
            Throwable var4 = null;

            T resutl;
            try {
                Span span = this.tracer.nextSpan();
                MDC.put("X-B3-TraceId", span.context().traceIdString());
                MDC.put("X-B3-SpanId", span.context().spanIdString());
                MDC.put("X-B3-ParentSpanId", span.context().parentIdString());
                span.name("new_thread_started").kind(Kind.SERVER).tag("thread_id", Thread.currentThread().getId() + "").tag("thread_name", Thread.currentThread().getName() + "");
                span.start();

                try {
                    Tracer.SpanInScope ws = this.tracer.withSpanInScope(span);
                    Throwable throwable = null;

                    try {
                        resutl = callable.call();
                    } catch (Throwable e) {
                        throwable = e;
                        throw e;
                    } finally {
                        if (ws != null) {
                            if (throwable != null) {
                                try {
                                    ws.close();
                                } catch (Throwable var44) {
                                    throwable.addSuppressed(var44);
                                }
                            } else {
                                ws.close();
                            }
                        }

                    }
                } catch (Error | RuntimeException e) {
                    span.error(e);
                    throw e;
                } finally {
                    span.finish();
                }
            } catch (Throwable e) {
                var4 = e;
                throw e;
            } finally {
                if (scope != null) {
                    if (var4 != null) {
                        try {
                            scope.close();
                        } catch (Throwable var43) {
                            var4.addSuppressed(var43);
                        }
                    } else {
                        scope.close();
                    }
                }

            }

            return resutl;
        };
    }
}