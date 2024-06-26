//package test.gateway1.filtter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//@Configuration
//public class GlobalFilterConfiguration {
//
//    @Bean
//    @Order(-1)
//    public GlobalFilter a() {
//        return (exchange, chain) -> {
//            log.info("first pre filter");
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                log.info("third post filter");
//            }));
//        };
//    }
//
//    @Bean
//    @Order(0)
//    public GlobalFilter b() {
//        return (exchange, chain) -> {
//            log.info("second pre filter");
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                log.info("second post filter");
//            }));
//        };
//    }
//
//    @Bean
//    @Order(1)
//    public GlobalFilter c() {
//        return (exchange, chain) -> {
//            log.info("third pre filter");
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                log.info("first post filter");
//            }));
//        };
//    }
//
//}
