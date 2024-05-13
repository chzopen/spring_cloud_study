package myZipkin.zipkin;


import brave.Tracing;
import brave.propagation.Propagation;
import brave.propagation.TraceContext;
import brave.sampler.Sampler;
import com.google.common.base.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zipkin2.Span;
import zipkin2.codec.Encoding;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.kafka.KafkaSender;
import zipkin2.reporter.okhttp3.OkHttpSender;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ZipkinContext {

    @Value("${spring.application.name}")
    private String localServiceName;

    @Value("${spring.zipkin.sender.type:kafka}")
    private String zipkinSenderType;

    @Value("${spring.zipkin.base-url:}")
    private String zipkinWebBaseUrl;

    @Value("${spring.zipkin.kafka.bootstrap-servers:}")
    private String kafkaBootstrapServers;

    @Value("${spring.zipkin.kafka.topic.name:zipkin}")
    private String topic;

    @Value("${spring.sleuth.sampler.probability:0.1}")
    private float samplerProbability;

    private Sender sender;
    private AsyncReporter<Span> reporter;
    private Tracing tracing;
    private TraceContext.Extractor<Headers> extractor;
    private TraceContext.Injector<Headers> injector;

    static final Propagation.Getter<Headers, String> HEADER_GETTER = (carrier, key) -> {
        Header header = carrier.lastHeader(key);
        return header == null ? null : new String(header.value(), Charsets.UTF_8);
    };

    static final Propagation.Setter<Headers, String> HEADER_SETTER = (carrier, key, value) -> {
        carrier.remove(key);
        carrier.add(key, value.getBytes(Charsets.UTF_8));
    };

    public ZipkinContext() {
    }

    @PostConstruct
    void initTracing() {
        if (StringUtils.equals(this.zipkinSenderType, "kafka")) {
            this.sender = this.buildKafkaSender();
            return ;
        } else {
            this.sender = this.buildWebSender();
        }

        this.reporter = AsyncReporter.builder(this.sender).build();
        Tracing.Builder builder = Tracing.newBuilder();
        builder.spanReporter(this.reporter);
        builder.sampler(Sampler.create(this.samplerProbability));
        builder.localServiceName(this.localServiceName);
        this.tracing = builder.build();
        this.extractor = this.tracing.propagation().extractor(HEADER_GETTER);
        this.injector = this.tracing.propagation().injector(HEADER_SETTER);
    }

    private Sender buildKafkaSender() {
        if (StringUtils.isBlank(this.kafkaBootstrapServers)) {
            return null;
//            throw new RuntimeException("没有配置属性【spring.zipkin.kafka.bootstrap-servers】");
        } else {
            Map<String, Object> props = new HashMap();
            props.put("bootstrap.servers", this.kafkaBootstrapServers);
            props.put("acks", "1");
            props.put("retries", 3);
            props.put("max.request.size", Integer.MAX_VALUE);
            return KafkaSender.newBuilder().topic(this.topic).overrides(props).encoding(Encoding.valueOf("JSON")).build();
        }
    }

    private Sender buildWebSender() {
        if (StringUtils.isBlank(this.zipkinWebBaseUrl)) {
            throw new RuntimeException("没有配置属性【spring.zipkin.base-url】");
        } else {
            return OkHttpSender.newBuilder().endpoint(this.zipkinWebBaseUrl).encoding(Encoding.valueOf("JSON")).build();
        }
    }

    public Sender getSender() {
        return this.sender;
    }

    public AsyncReporter<Span> getReporter() {
        return this.reporter;
    }

    public Tracing getTracing() {
        return this.tracing;
    }

    public TraceContext.Extractor<Headers> getExtractor() {
        return this.extractor;
    }

    public TraceContext.Injector<Headers> getInjector() {
        return this.injector;
    }

}
