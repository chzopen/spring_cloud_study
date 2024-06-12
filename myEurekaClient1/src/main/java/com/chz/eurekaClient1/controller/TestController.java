package com.chz.eurekaClient1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hello")
    public String hello() {
        log.info("chz >>> TestController.hello()");
        try {
            if( Math.abs(ThreadLocalRandom.current().nextInt() % 2)==0 ) {
                log.info("chz >>> TestController.hello(), sleep");
                Thread.sleep(3000L);
            }
        } catch (InterruptedException e) {
            log.info("interrupt", e);
        }
        log.info("chz >>> TestController.hello(), result");
        return "Hello: " + serverPort;
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "Hello2: " + serverPort;
    }

    @GetMapping("/hello3")
    public String hello3() {
        try {
            Thread.sleep(60000L);
        } catch (InterruptedException e) {
        }
        return "Hello3, Spring Boot Web!";
    }

    public static void main(String[] args) {
        int i = ThreadLocalRandom.current().nextInt();
        System.out.println(i);
        System.out.println(-2 % 2);
        System.out.println(3 % 2 == 1);
        System.out.println(2 % 2 == 0);
        System.out.println(1 % 2 == 1);
        System.out.println(0 % 2 == 0);
    }

}