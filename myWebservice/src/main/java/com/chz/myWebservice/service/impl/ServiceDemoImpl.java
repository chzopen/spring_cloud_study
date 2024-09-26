package com.chz.myWebservice.service.impl;

import com.chz.myWebservice.service.ServiceDemo;
import org.springframework.stereotype.Component;

import javax.jws.WebParam;
import javax.jws.WebService;

@Component
@WebService(name = "ServiceDemo",
        targetNamespace = "http://www.baidu.com",
        endpointInterface = "com.chz.myWebservice.service.ServiceDemo")
public class ServiceDemoImpl implements ServiceDemo {

    @Override
    public String emrService(@WebParam String data) {
        if (null == data || "".equals(data.trim())) {
            return "传入的参数为空";
        }
        return "调用成功，传入的参数是：" + data;
    }
}
