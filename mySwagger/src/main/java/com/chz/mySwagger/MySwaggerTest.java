package com.chz.mySwagger;

import com.chz.mySwagger.controller1.Test1Controller;
import com.chz.mySwagger.controller2.Test2Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackageClasses = {
        Test1Controller.class,
        Test2Controller.class,
})
public class MySwaggerTest {

    public static void main(String[] args) {
        SpringApplication.run(MySwaggerTest.class, args);
    }
}