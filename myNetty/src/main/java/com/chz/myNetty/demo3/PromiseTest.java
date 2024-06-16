package com.chz.myNetty.demo3;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PromiseTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        NioEventLoopGroup loop = new NioEventLoopGroup();
        DefaultPromise<String> promise = new DefaultPromise<>(loop.next());

        promise.addListener(new GenericFutureListener<Future<? super String>>() {
            @Override
            public void operationComplete(Future<? super String> future) throws Exception {
                log.info("chz >>> GenericFutureListener.operationComplete()");
            }
        });

        new Thread(()->{
            try {
                Thread.sleep(2000L);
                log.info("chz >>> 准备调用【promise.setSuccess()】");
                promise.setSuccess("i am finished");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        log.info("chz >>> 准备调用【promise.get()】");
        String result = promise.get();   // 这里在调用【promise.setSuccess】之前会被阻塞
        System.out.println("result: " + result);
    }

}
