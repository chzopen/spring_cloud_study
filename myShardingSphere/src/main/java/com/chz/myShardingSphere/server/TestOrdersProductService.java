package com.chz.myShardingSphere.server;

import com.chz.myShardingSphere.persistence.mapper.OrdersProductMapper;
import com.chz.myShardingSphere.persistence.po.OrdersProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestOrdersProductService {

    @Autowired
    private OrdersProductMapper ordersProductMapper;

    @Transactional
    public void addOrderProduct(long id, long customerId, long orderId, long productId)
    {
        OrdersProduct ordersProduct = new OrdersProduct();
        ordersProduct.setId(id);
        ordersProduct.setCustomerId(customerId);
        ordersProduct.setOrderId(orderId);
        ordersProduct.setProductId(productId);
        ordersProductMapper.insert(ordersProduct);
    }

    @Transactional
    public List<OrdersProduct> selectOrderProduct(Long customerId, Long orderId)
    {
        return ordersProductMapper.selectOrder(customerId, orderId);
    }

    @Transactional
    public List<OrdersProduct> selectOrderProduct2(Long customerId, List<Long> orderIds)
    {
        return ordersProductMapper.selectOrder2(customerId, orderIds);
    }

}
