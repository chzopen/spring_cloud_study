package test.application1.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import test.application1.persistence.po.User;

@Mapper
public interface UserMapper extends BaseMapper<User>
{

    User selectUserById(Long id);

}