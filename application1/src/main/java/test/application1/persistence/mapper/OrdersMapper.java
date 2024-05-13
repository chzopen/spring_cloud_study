package test.application1.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import test.application1.persistence.po.Orders;

@Repository
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}