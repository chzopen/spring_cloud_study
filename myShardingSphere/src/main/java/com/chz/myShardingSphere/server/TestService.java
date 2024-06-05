package com.chz.myShardingSphere.server;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chz.myShardingSphere.persistence.mapper.OrdersMapper;
import com.chz.myShardingSphere.persistence.po.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Transactional
    public void testAddOrder(long id)
    {
        Orders orders = new Orders();
        orders.setId(id);
        orders.setCustomerId(id);
        orders.setOrderType(id);
        orders.setAmount((double)id);
        ordersMapper.insert(orders);
    }

    @Transactional
    public List<Orders> testSelect()
    {
        List<Orders> orders = ordersMapper.testSelect(1L);
        return orders;
    }

    @Transactional
    public void testDeleteOrder()
    {
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        ordersMapper.delete(queryWrapper);
    }

}
