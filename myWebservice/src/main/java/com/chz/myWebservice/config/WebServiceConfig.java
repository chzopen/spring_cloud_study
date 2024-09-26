package com.chz.myWebservice.config;

import com.chz.myWebservice.service.HelloWorldService;
import com.chz.myWebservice.service.ServiceDemo;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private ServiceDemo serviceDemo;

    @Autowired
    private HelloWorldService helloWorldService;

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean("endpoint1")
    public Endpoint endpoint1(SpringBus springBus) {
        EndpointImpl endpoint = new EndpointImpl(springBus, helloWorldService);
        endpoint.publish("/hello");
        return endpoint;
    }

    @Bean("endpoint2")
    public Endpoint endpoint2(SpringBus springBus) {
        EndpointImpl endpoint = new EndpointImpl(springBus, serviceDemo);
        endpoint.publish("/serviceDemo");
        return endpoint;
    }
}
