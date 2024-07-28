package com.chz.myShardingSphere.controller;

import com.chz.myShardingSphere.persistence.po.OrdersProduct;
import com.chz.myShardingSphere.server.TestOrdersProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/ordersProduct")
public class TestOrdersProductController {

    @Autowired
    private TestOrdersProductService testOrdersProductService;

    @GetMapping("/addOrderProduct")
    public String addOrderProduct(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "customerId") Long customerId,
            @RequestParam(value = "orderId") Long orderId,
            @RequestParam(value = "productId") Long productId
    ) {
        testOrdersProductService.addOrderProduct(id, customerId, orderId, productId);
        return "addOrderProduct";
    }

    @GetMapping("/selectOrderProduct")
    public List<OrdersProduct> selectOrderProduct(
            @RequestParam(value = "customerId") Long customerId,
            @RequestParam(value = "orderId", required = false) Long orderId
    ) {
        return testOrdersProductService.selectOrderProduct(customerId, orderId);
    }

    @GetMapping("/selectOrderProduct2")
    public List<OrdersProduct> selectOrderProduct2(
            @RequestParam(value = "customerId") Long customerId,
            @RequestParam(value = "orderIds", required = false) String orderIds
    ) {
        List<Long> orderIdList = new ArrayList<>();
        if( StringUtils.isNoneBlank(orderIds) ){
            orderIdList.addAll(Arrays.stream(orderIds.split(",")).filter(s -> StringUtils.isNoneBlank(s)).map(s -> Long.valueOf(s)).collect(Collectors.toList()));
        }
        return testOrdersProductService.selectOrderProduct2(customerId, orderIdList);
    }


}