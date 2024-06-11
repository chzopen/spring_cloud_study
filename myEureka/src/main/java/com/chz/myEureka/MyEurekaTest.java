package com.chz.myEureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MyEurekaTest {

    public static void main(String[] args) {
        SpringApplication.run(MyEurekaTest.class, args);
    }
}
