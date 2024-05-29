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
@RequestMapping("/restTemplate")
public class TestRestTemplateController
{
    @Autowired
    @Qualifier("staticRestTemplate")
    private RestTemplate staticRestTemplate;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @GetMapping("/hello1")
    public String hello1() {
        String result = staticRestTemplate.getForObject("http://localhost:8281/test/test1", String.class);
        return "Hello1: "+ result;
    }

    @GetMapping("/hello2")
    public String hello2() {
        String result = restTemplate.getForObject("http://myNacosServer/test/test1", String.class);
        return "Hello2: "+ result;
    }
}