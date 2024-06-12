package com.chz.myFeignClient.controller;

import com.chz.myFeignClient.feign.Test1FeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test2")
public class Test2Controller {

    @Autowired
    private Test1FeignClient test1FeignClient;

    @GetMapping("/test")
    public String test() {
        log.info("chz >>> Test2Controller.test()");
        String result = test1FeignClient.hello();
        log.info("chz >>> Test2Controller.test(): {}", result);
        return result;
    }

}