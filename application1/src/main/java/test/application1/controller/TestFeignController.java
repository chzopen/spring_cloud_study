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

@Slf4j
@RestController
@RequestMapping("/feign")
public class TestFeignController {

    @Value("${test1:}")
    private String test1;

    @Value("${test2:}")
    private String test2;

    @Autowired
    private StaticEurekaClient1 staticEurekaClient1;

    @Autowired
    private EurekaClient1 eurekaClient1;

    @Autowired
    @Qualifier("staticRestTemplate")
    private RestTemplate staticRestTemplate;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello() {
        String result = staticEurekaClient1.hello();
        return "Hello: "+ result;
    }

    @GetMapping("/hello2")
    public String hello2() {
        String result = eurekaClient1.hello2();
        return "Hello2: "+ result;
    }

    @GetMapping("/hello3")
    public String hello3() {
        String result = staticRestTemplate.getForObject("http://localhost:8001/test/hello", String.class);
        return "Hello3: "+ result;
    }

    @GetMapping("/hello4")
    public String hello4() {
        String result = restTemplate.getForObject("http://eurekaClient1/test/hello", String.class);
        return "Hello3: "+ result;
    }

    @HystrixCommand(fallbackMethod = "hello5Fallback")
    @GetMapping("/hello5")
    public String hello5() throws Exception {
        throw new Exception("hello5 exception");
    }

    @GetMapping("/hello5Fallback")
    public String hello5Fallback() {
        return "hello5Fallback";
    }

    @GetMapping("/exception")
    public String exception() throws Exception {
        throw new Exception("3333");
    }

    @GetMapping("/test1")
    public String test1() {
        return "test1="+test1;
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2="+test2;
    }

    @GetMapping("/forward")
    public String forward() {
        return "forward!";
    }

    @GetMapping("/error")
    public String error() {
        return "error!";
    }

}