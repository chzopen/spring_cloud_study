package com.chz.myTcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MyTccTest {
    public static void main(String[] args) {
        SpringApplication.run(MyTccTest.class, args);
    }
}