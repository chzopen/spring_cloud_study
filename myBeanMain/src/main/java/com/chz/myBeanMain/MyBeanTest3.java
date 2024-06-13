package com.chz.myBeanMain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
        basePackages = "com.chz.myBeanMain.branch"
)
@SpringBootApplication(
        scanBasePackageClasses = MyBeanTest3.class
)
public class MyBeanTest3 {

    public static void main(String[] args) {
        SpringApplication.run(MyBeanTest3.class, args);
    }

}