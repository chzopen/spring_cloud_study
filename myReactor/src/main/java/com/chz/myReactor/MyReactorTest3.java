package com.chz.myReactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

public class MyReactorTest3 {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        Flux<Integer> flux2 = flux.map(value -> value * 2);
        flux2.subscribe(new BaseSubscriber<>() {

            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("绑定了..." + subscription);
                request(1);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("数据到达，正在处理：" + value);
                request(1);
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("流正常结束...");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                System.out.println("流异常..." + throwable);
            }

            @Override
            protected void hookOnCancel() {
                System.out.println("流被取消...");
            }

            @Override
            protected void hookFinally(SignalType type) {
                System.out.println("最终回调...一定会被执行");
            }
        });
    }
}