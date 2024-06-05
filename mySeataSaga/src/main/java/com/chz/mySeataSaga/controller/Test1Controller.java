package com.chz.mySeataSaga.controller;

import io.seata.saga.engine.StateMachineEngine;
import io.seata.saga.statelang.domain.StateMachineInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/test1")
public class Test1Controller {

    @Autowired
    private StateMachineEngine stateMachineEngine;

    @RequestMapping(value = "/test1Success", method = RequestMethod.GET)
    public boolean test1Success(@RequestParam("p1")String p1)
    {
        log.info("Test1Controller::test1Success");

        Map<String, Object> startParams = new HashMap<>(3);
        String businessKey = String.valueOf(System.currentTimeMillis());
        startParams.put("businessKey", businessKey);
        startParams.put("p1", "p1-1");
        startParams.put("p2", "p2-1");

        StateMachineInstance inst = stateMachineEngine.startWithBusinessKey("test", null, businessKey, startParams);
        log.info("test1Success: {}", inst.getStatus().getStatusString());

        return true;
    }

    @RequestMapping(value = "/test1Fail", method = RequestMethod.GET)
    public String test1Fail(@RequestParam("p1")String p1)
    {
        log.info("Test1Controller::test1Fail");
        throw new RuntimeException("test1Fail");
    }

}
