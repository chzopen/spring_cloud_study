package com.chz.gateway1.filtter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class DynamicEverythingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("chz >>> DynamicEverythingFilter.filter()");
        return chain.filter(exchange);
    }
  
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}