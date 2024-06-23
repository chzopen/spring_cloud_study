package com.chz.myTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class MyTestCompletableFutureTest
{
    public static void main1(String[] args) throws ExecutionException, InterruptedException
    {
        Future<String> completableFuture = CompletableFuture.completedFuture("Hello");
        String s = completableFuture.get();
        log.info(s);
    }

    public static void main2(String[] args) throws ExecutionException, InterruptedException
    {
        log.info("start");
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        new Thread(()->{
            try {
                Thread.sleep(2000);
                log.info("准备调用：complete()");
                completableFuture.complete("Hello");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        log.info("准备调用：completableFuture.get()");
        String s = completableFuture.get();     // 这里会被阻塞
        log.info("result: {}", s);
    }

    public static void main3(String[] args) throws ExecutionException, InterruptedException
    {
        log.info("start");
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        new Thread(()->{
            try {
                Thread.sleep(2000);
                log.info("准备调用：cancel()");
                completableFuture.cancel(true);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        log.info("准备调用：completableFuture.get()");
        try {
            String s = completableFuture.get();     // 这里会被阻塞
            log.info("result: {}", s);
        } catch (Exception e) {
            log.info("exception: {}, {}", e.getClass().getSimpleName(), e.getMessage(), e);
        }
    }

    public static void main4(String[] args) throws ExecutionException, InterruptedException
    {
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(()->{
            return "supplyAsync";
        });
        String s = supplyAsync.get();
        log.info("result: " + s);
    }

    public static void main5(String[] args) throws ExecutionException, InterruptedException
    {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "1");
        stringCompletableFuture = stringCompletableFuture.thenCompose(new Function<String, CompletionStage<String>>() {
            @Override
            public CompletionStage<String> apply(String s) {
                log.info("s1: " + s);
                return CompletableFuture.supplyAsync(() -> s + " 2");
            }
        });
        stringCompletableFuture = stringCompletableFuture.thenCompose(new Function<String, CompletionStage<String>>() {
            @Override
            public CompletionStage<String> apply(String s) {
                log.info("s2: " + s);
                return CompletableFuture.supplyAsync(() -> s + " 3");
            }
        });
        String s = stringCompletableFuture.get();
        log.info("s3: " + s);
    }

    public static void main6(String[] args) throws ExecutionException, InterruptedException
    {
        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenCombine(
                        CompletableFuture.supplyAsync(() -> " World"),
                        (s1, s2) -> s1 + s2
                );
        String s = completableFuture.get();
        log.info(s);
    }

    public static void main7(String[] args)
    {
        CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenAcceptBoth(
                        CompletableFuture.supplyAsync(() -> " World"),
                        (s1, s2) -> {
                            log.info("result: " + (s1 + s2));
                        }
                );
    }

    public static void main(String[] args) throws InterruptedException
    {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("1");
            return "Hello";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            log.info("2");
            return "Beautiful";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            log.info("3");
            return "World";
        });
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future1, future2, future3);
        voidCompletableFuture.join();
    }

    public static void main9(String[] args)
    {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

        String combined = Stream.of(future1, future2, future3)
                                .map(CompletableFuture::join)
                                .collect(Collectors.joining(" "));

        log.info(combined);
    }

    public static void main10(String[] args) throws ExecutionException, InterruptedException
    {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            log.info("supplyAsync");
            throw new RuntimeException("Computation error!");
        });
        CompletableFuture<String> handle = completableFuture.handle((s, t) -> {
            log.info("handle");
            return (s != null) ? s : "Hello, Stranger!";
        });
        String s = handle.get();
        log.info("result: " + s);
    }

}
