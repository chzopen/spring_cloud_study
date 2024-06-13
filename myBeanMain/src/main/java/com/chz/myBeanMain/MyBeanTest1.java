package com.chz.myBeanMain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = "com.chz.myBeanMain.branch",
        scanBasePackageClasses = MyBeanTest1.class
)
public class MyBeanTest1 {

    public static void main(String[] args) {
        SpringApplication.run(MyBeanTest1.class, args);
    }

}