package test.eurekaClient1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hello")
    public String hello() {
        return "Hello: " + serverPort;
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "Hello2: " + serverPort;
    }

    @GetMapping("/hello3")
    public String hello3() {
        try {
            Thread.sleep(60000L);
        } catch (InterruptedException e) {
        }
        return "Hello3, Spring Boot Web!";
    }

}