package com.chz.mySpringBootTest.controller;

import com.chz.mySpringBootTest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test()
    {
        return "TestController:test()";
    }

    @GetMapping("/test2")
    public String test2()
    {
        return testService.test();
    }

}
