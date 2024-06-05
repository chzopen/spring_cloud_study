package com.chz.myTcc.controller;

import com.chz.myTcc.feign.Test2FeignClient;
import com.chz.myTcc.feign.Test3FeignClient;
import lombok.extern.slf4j.Slf4j;
import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test1")
public class Test1Controller {

    @Autowired
    private Test2FeignClient test2FeignClient;

    @Autowired
    private Test3FeignClient test3FeignClient;

    @RequestMapping(value = "/test1Success", method = RequestMethod.GET)
    @Compensable(confirmMethod = "testConfirm", cancelMethod = "testCancel")
    public String test1Success(@RequestParam("p1")String p1)
    {
        log.info("Test1Controller::test1Success");
        test2FeignClient.test1Success(p1);
        test3FeignClient.test1Success(p1);
        return "test1Success: success";
    }

    @RequestMapping(value = "/test1Fail", method = RequestMethod.GET)
    @Compensable(confirmMethod = "testConfirm", cancelMethod = "testCancel")
    public String test1Fail(@RequestParam("p1")String p1)
    {
        log.info("Test1Controller::test1Fail");
        test2FeignClient.test1Success(p1);
        test3FeignClient.test1Success(p1);
        throw new RuntimeException("test1Fail");
    }


    @RequestMapping(value = "/test2Fail", method = RequestMethod.GET)
    @Compensable(confirmMethod = "testConfirm", cancelMethod = "testCancel")
    public String test2Fail(@RequestParam("p1")String p1)
    {
        log.info("Test1Controller::test2Fail");
        test2FeignClient.test1Success(p1);
        test3FeignClient.test1Fail(p1);
        return "test2Fail: success";
    }

    public void testConfirm(@RequestParam("p1")String p1)
    {
        log.info("Test1Controller::testConfirm");
    }

    public void testCancel(@RequestParam("p1")String p1)
    {
        log.info("Test1Controller::testCancel");
    }

}
