package myZipkin.zipkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.cloud.sleuth.zipkin2.ZipkinAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ZipkinContext.class})
@AutoConfigureBefore({ZipkinAutoConfiguration.class})
public class SenderConfiguration {

    @Autowired
    ZipkinContext zipkinContext;

    public SenderConfiguration() {
    }

//    @Bean({"zipkinSender"})
//    @Primary
//    Sender sender() {
//        return this.zipkinContext.getSender();
//    }

}