package test.application2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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