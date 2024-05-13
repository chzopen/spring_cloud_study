package test.application1.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.application1.persistence.po.Orders;
import test.application1.persistence.mapper.OrdersMapper;

@Slf4j
@RestController
@RequestMapping("/shardingJdbc")
public class TestShardingJdbcController {

    @Autowired
    private OrdersMapper ordersMapper;

    @GetMapping("/addOrders")
    public void addOrders(){
        for (int i = 1; i <=10 ; i++) {
            Orders orders = new Orders();
            orders.setId(i);
            orders.setCustomerId(i);
            orders.setOrderType(i);
            orders.setAmount(1000.0*i);
            ordersMapper.insert(orders);
        }
    }

    @GetMapping("/deleteOrders")
    public void deleteOrders(){
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        ordersMapper.delete(queryWrapper);
    }

    @GetMapping("/queryOrders")
    public String queryOrders(){
        Orders orders = ordersMapper.selectById(1);
        return JSON.toJSONString(orders);
    }


}