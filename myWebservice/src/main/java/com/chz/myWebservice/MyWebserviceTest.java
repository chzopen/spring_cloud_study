package com.chz.myWebservice;

import com.chz.myWebservice.service.impl.HelloWorldServiceImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyWebserviceTest
{

    public static void main(String[] args)
    {
        SpringApplication.run(MyWebserviceTest.class, args);
        System.out.println("ddd");

//        // 创建 CXF 的服务工厂
//        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
//        factoryBean.setServiceBean(new HelloWorldServiceImpl());
//        factoryBean.setAddress("/hello");
//        factoryBean.create();
    }
}