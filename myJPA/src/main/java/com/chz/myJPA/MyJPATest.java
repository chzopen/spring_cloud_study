package com.chz.myJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.chz.myJPA.repository")  // repository 所在的包
@EntityScan(basePackages = "com.chz.myJPA.entity") // 实体所在的包
public class MyJPATest {
    public static void main(String[] args) {
        SpringApplication.run(MyJPATest.class, args);
    }
}