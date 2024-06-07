package com.chz.myReactor;

import reactor.core.publisher.Flux;

public class MyReactorTest6 {

    public static void main(String[] args) throws InterruptedException
    {
        Flux.range(1,9).subscribe(System.out::println);

        String[] arr = {"A","B","C"};
        Flux.fromArray(arr).subscribe(System.out::println);
    }

}