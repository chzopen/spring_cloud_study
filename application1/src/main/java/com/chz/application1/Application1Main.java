package com.chz.application1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@ServletComponentScan
@EnableFeignClients
@EnableHystrix
//@EnableApolloConfig
@SpringBootApplication(
        scanBasePackages = {
                "test"
        }
)
public class Application1Main {

    public static void main(String[] args) {
        SpringApplication.run(Application1Main.class, args);
    }
}
