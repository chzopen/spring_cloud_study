package com.chz.myShardingSphere.server;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chz.myShardingSphere.persistence.mapper.OrdersMapper;
import com.chz.myShardingSphere.persistence.po.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestOrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Transactional
    public void addOrder(Long id, Long customerId, String orderType, Double amount)
    {
        Orders orders = new Orders();
        orders.setId(id);
        orders.setCustomerId(customerId);
        orders.setOrderType(orderType);
        orders.setAmount(amount);
        ordersMapper.insert(orders);
    }

    @Transactional
    public List<Orders> selectOrder(Long customerId, Long orderId)
    {
        List<Orders> orders = ordersMapper.selectOrder(customerId, orderId);
        return orders;
    }

    @Transactional
    public void deleteOrder()
    {
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        ordersMapper.delete(queryWrapper);
    }

}
