package com.chz.myApplication1.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chz.myApplication1.persistence.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>
{

    User selectUserById(Long id);

}