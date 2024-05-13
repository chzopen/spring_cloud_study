package test.application1.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import test.application1.feign.EurekaClient1;
import test.application1.feign.StaticEurekaClient1;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/test1")
    public String test1() {
        return "test1: " + applicationName;
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2";
    }

}