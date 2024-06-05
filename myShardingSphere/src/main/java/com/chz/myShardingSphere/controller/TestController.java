package com.chz.myShardingSphere.controller;

import com.alibaba.fastjson2.JSON;
import com.chz.myShardingSphere.persistence.po.Orders;
import com.chz.myShardingSphere.server.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private TestService testService;

    @GetMapping("/testAddOrder")
    public String testAddOrder(
            @RequestParam(value = "id", defaultValue = "1") Long id
    ) {
        testService.testAddOrder(id);
        return "testAddOrder";
    }

    @GetMapping("/testSelectOrder")
    public String testSelectOrder(
            @RequestParam(value = "customerId", required = false) Long customerId
    ) {
        List<Orders> orders = testService.testSelectOrder(customerId);
        return JSON.toJSONString(orders);
    }

    @GetMapping("/testDeleteOrder")
    public String testDeleteOrder() {
        testService.testDeleteOrder();
        return "success";
    }

}