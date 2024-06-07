package com.chz.myReactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.util.Date;

public class MyReactorTest4 {

    public static void main(String[] args) throws InterruptedException {
        test1();
        test2();
    }

    private static void test1() throws InterruptedException
    {
        Mono<String> defer = Mono.defer(() -> {
            return Mono.just("time: " + new Date().toString());
        });
        defer.subscribe(System.out::println);
        Thread.sleep(1000);
        defer.subscribe(System.out::println);
    }

    private static void test2() throws InterruptedException
    {
        Mono<String> defer = Mono.just("time: " + new Date().toString());
        defer.subscribe(System.out::println);
        Thread.sleep(1000);
        defer.subscribe(System.out::println);
    }
}