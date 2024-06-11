package com.chz.myFeignClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MyFeignClientTest {

    public static void main(String[] args) {
        SpringApplication.run(MyFeignClientTest.class, args);
    }
}