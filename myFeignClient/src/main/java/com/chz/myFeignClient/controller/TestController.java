package com.chz.myFeignClient.controller;

import com.chz.myFeignClient.feign.MyEurekaClient1FeignClient;
import com.chz.myFeignClient.feign.TestBaiduFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestBaiduFeignClient testBaiduFeignClient;


    @Autowired
    private MyEurekaClient1FeignClient myEurekaClient1FeignClient;

    @GetMapping("/test")
    public String test() {
        return testBaiduFeignClient.testGet();
    }

    @GetMapping("/test2")
    public String test2() {
        return myEurekaClient1FeignClient.hello();
    }

}