package com.chz.myNacosClient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/feign")
public class TestRestTemplateController {

    @Value("${test1:}")
    private String test1;

    @Value("${test2:}")
    private String test2;

    @Autowired
    @Qualifier("staticRestTemplate")
    private RestTemplate staticRestTemplate;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @GetMapping("/hello1")
    public String hello3() {
        String result = staticRestTemplate.getForObject("http://localhost:8281/test/test1", String.class);
        return "Hello1: "+ result;
    }

    @GetMapping("/hello2")
    public String hello4() {
        String result = restTemplate.getForObject("http://myNacosServer/test/test1", String.class);
        return "Hello2: "+ result;
    }

    @GetMapping("/exception")
    public String exception() throws Exception {
        throw new Exception("3333");
    }

    @GetMapping("/test1")
    public String test1() {
        return "test1="+test1;
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2="+test2;
    }

    @GetMapping("/forward")
    public String forward() {
        return "forward!";
    }

    @GetMapping("/error")
    public String error() {
        return "error!";
    }

}