package com.chz.myWebFlux.config;

import com.chz.myWebFlux.handler.CityHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class CityRouter {


    @Bean
    public RouterFunction<ServerResponse> routeCity(CityHandler cityHandler) {
        System.out.println("CityRouter::routeCity");
        return RouterFunctions.route(
                RequestPredicates.GET("/router/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                cityHandler::helloCity
        );
    }

    @Bean
    public RouterFunction<ServerResponse> routeCity2(CityHandler cityHandler) {
        System.out.println("CityRouter::routeCity2");
        return RouterFunctions.route(
                RequestPredicates.GET("/router/hello2").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                cityHandler::helloCity
        );
    }

//    public static void main(String[] args) {
//        CityHandler cityHandler = new CityHandler();
//        Function<ServerRequest, Mono<ServerResponse>> helloCity = cityHandler::helloCity;
//    }

}
