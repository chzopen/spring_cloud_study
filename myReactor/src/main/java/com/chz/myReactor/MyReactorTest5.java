package com.chz.myReactor;

import reactor.core.publisher.Mono;

import java.util.Date;

public class MyReactorTest5 {

    public static void main(String[] args) throws InterruptedException {
        Mono    .just("hello world").doFirst(() -> {
                    System.out.println("doFirst");
                    throw new RuntimeException("doFirst err");
                })
                .doFinally((d)->{
                    System.out.println("doFinally : " + d);
                })
                .doOnError(throwable -> {
                    System.out.println("error is : " + throwable.getMessage());
                })
                .subscribe(System.out::println);
    }

    private static void test1() throws InterruptedException
    {
    }

    private static void test2() throws InterruptedException
    {
    }
}