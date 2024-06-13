package com.chz.myBeanMain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
        basePackages = "com.chz.myBeanMain.branch",
        basePackageClasses = MyBeanTest2.class
)
@SpringBootApplication(
)
public class MyBeanTest2 {

    public static void main(String[] args) {
        SpringApplication.run(MyBeanTest2.class, args);
    }

}