package com.chz.myApplication1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v3")
public class TestApiV3Controller {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/test1")
    public String test1() {
        return "test1: " + applicationName;
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2";
    }

}