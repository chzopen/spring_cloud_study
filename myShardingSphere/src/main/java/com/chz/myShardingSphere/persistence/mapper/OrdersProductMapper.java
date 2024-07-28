package com.chz.myShardingSphere.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chz.myShardingSphere.persistence.po.Orders;
import com.chz.myShardingSphere.persistence.po.OrdersProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrdersProductMapper extends BaseMapper<OrdersProduct>
{

    List<OrdersProduct> selectOrder(@Param("customerId") Long customerId, @Param("orderId") Long orderId);

    List<OrdersProduct> selectOrder2(@Param("customerId") Long customerId, @Param("orderIds") List<Long> orderIds);

}