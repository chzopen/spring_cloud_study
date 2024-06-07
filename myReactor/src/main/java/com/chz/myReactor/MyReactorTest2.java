package com.chz.myReactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MyReactorTest2  {

    public static void main(String[] args) {
        System.out.println("--------------------------------");

        Mono<Integer> mono = Mono.just(1);
        mono.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
        System.out.println("--------------------------------");

        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        flux.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
        System.out.println("--------------------------------");

        Flux<Integer> flux2 = flux.map(value -> value * 2);
        flux2.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
        System.out.println("--------------------------------");



    }
}