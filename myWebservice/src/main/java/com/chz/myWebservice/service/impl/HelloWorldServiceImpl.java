package com.chz.myWebservice.service.impl;

import com.chz.myWebservice.service.HelloWorldService;
import org.apache.cxf.feature.Features;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@Component
@WebService(endpointInterface = "com.chz.myWebservice.service.HelloWorldService")
@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class HelloWorldServiceImpl implements HelloWorldService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }
}
