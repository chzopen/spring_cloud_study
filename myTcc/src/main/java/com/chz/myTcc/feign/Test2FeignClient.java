package com.chz.myTcc.feign;

import org.mengyun.tcctransaction.api.EnableTcc;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "Test2FeignClient", name = "myTcc", url = "http://localhost:8082/myTcc/test2")
public interface Test2FeignClient {

    @EnableTcc
    @RequestMapping(value = "/test1Success", method = RequestMethod.GET)
    String test1Success(@RequestParam("p1")String p1);

    @EnableTcc
    @RequestMapping(value = "/test1Fail", method = RequestMethod.GET)
    String test1Fail(@RequestParam("p1")String p1);

}