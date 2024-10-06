package com.chz.mySpringBootTest.service.impl;

import com.chz.mySpringBootTest.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public String test()
    {
        return "TestServiceImpl:test()";
    }

}
