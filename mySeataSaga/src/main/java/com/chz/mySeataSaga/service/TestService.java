package com.chz.mySeataSaga.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("testService")
public class TestService {

    public boolean test1(String p1, String p2)
    {
        log.info("TestService::test1()");
        return true;
    }

    public boolean test2(String p1, String p2)
    {
        log.info("TestService::test2()");
        return true;
    }

}
