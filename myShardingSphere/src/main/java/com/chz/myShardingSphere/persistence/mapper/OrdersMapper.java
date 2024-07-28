package com.chz.myShardingSphere.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chz.myShardingSphere.persistence.po.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrdersMapper extends BaseMapper<Orders>
{

    List<Orders> selectOrder(@Param("customerId") Long customerId, @Param("orderId") Long orderId);

}