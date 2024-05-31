package com.chz.myWeb;

import com.chz.myWeb.helper.ShutdownHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication()
public class MyWebTest {

    public static void main(String[] args) {
        registerHook();
        SpringApplication.run(MyWebTest.class, args);
    }

    private static void registerHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // 当pod关闭的时候会触发这个回调
            System.out.println(LocalDateTime.now() + "-->Hook.run 1....");
            // 关闭spring boot
            ShutdownHelper.shutdown();
            System.out.println(LocalDateTime.now() + "-->Hook.run 2....");
        }));
    }
}