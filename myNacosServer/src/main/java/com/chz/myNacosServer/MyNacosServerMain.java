package com.chz.myNacosServer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MyNacosServerMain {
    public static void main(String[] args) {
        SpringApplication.run(MyNacosServerMain.class, args);
    }
}