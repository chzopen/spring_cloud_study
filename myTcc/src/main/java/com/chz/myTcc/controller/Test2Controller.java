package com.chz.myTcc.controller;

import lombok.extern.slf4j.Slf4j;
import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test2")
public class Test2Controller
{
    @RequestMapping(value = "/test1Success", method = RequestMethod.GET)
    @Compensable(confirmMethod = "testConfirm", cancelMethod = "testCancel")
    public String test1Success(@RequestParam("p1")String p1)
    {
        log.info("Test2Controller::test1Success");
        return "test1: success";
    }

    @RequestMapping(value = "/test1Fail", method = RequestMethod.GET)
    @Compensable(confirmMethod = "testConfirm", cancelMethod = "testCancel")
    public String test1Fail(@RequestParam("p1")String p1)
    {
        log.info("Test2Controller::test1Fail");
        throw new RuntimeException("test1Fail");
    }

    public void testConfirm(@RequestParam("p1")String p1)
    {
        log.info("Test2Controller::testConfirm");
    }

    public void testCancel(@RequestParam("p1")String p1)
    {
        log.info("Test2Controller::testCancel");
    }

}
