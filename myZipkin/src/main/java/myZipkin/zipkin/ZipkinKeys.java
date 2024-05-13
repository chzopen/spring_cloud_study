package myZipkin.zipkin;

public class ZipkinKeys {
    public static final String TRACE_ID = "X-B3-TraceId";
    public static final String SPAN_ID = "X-B3-SpanId";
    public static final String PARENT_SPAN_ID = "X-B3-ParentSpanId";
    public static final String SAMPLED = "X-B3-Sampled";
    public static final String HTTP_HEADER_TRACE_ID = "x-b3-traceid";
    public static final String HTTP_HEADER_SPAN_ID = "x-b3-spanid";
    public static final String HTTP_HEADER_PARENT_SPAN_ID = "x-b3-parentspanid";
    public static final String HTTP_HEADER_SAMPLED = "x-b3-sampled";

    public ZipkinKeys() {
    }
}