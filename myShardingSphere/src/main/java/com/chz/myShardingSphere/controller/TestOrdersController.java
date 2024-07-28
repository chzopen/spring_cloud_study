package com.chz.myShardingSphere.controller;

import com.alibaba.fastjson2.JSON;
import com.chz.myShardingSphere.persistence.po.Orders;
import com.chz.myShardingSphere.server.TestOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orders")
public class TestOrdersController {

    @Autowired
    private TestOrdersService testOrdersService;

    @GetMapping("/addOrder")
    public String addOrder(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "customerId") Long customerId,
            @RequestParam(value = "orderType") String orderType,
            @RequestParam(value = "amount") Double amount
    ) {
        testOrdersService.addOrder(id, customerId, orderType, amount);
        return "addOrder";
    }

    @GetMapping("/selectOrder")
    public String selectOrder(
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "orderId", required = false) Long orderId
    ) {
        List<Orders> orders = testOrdersService.selectOrder(customerId, orderId);
        return JSON.toJSONString(orders);
    }

    @GetMapping("/deleteOrder")
    public String deleteOrder() {
        testOrdersService.deleteOrder();
        return "success";
    }

}