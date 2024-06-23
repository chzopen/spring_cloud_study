package com.chz.mySpringSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class MySpringSecurityMain
{
    public static void main(String[] args)
    {
        SpringApplication.run(MySpringSecurityMain.class, args);
    }
}