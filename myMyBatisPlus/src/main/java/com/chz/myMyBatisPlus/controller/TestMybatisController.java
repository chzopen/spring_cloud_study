package com.chz.myMyBatisPlus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chz.myMyBatisPlus.persistence.mapper.UserMapper;
import com.chz.myMyBatisPlus.persistence.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mybatis")
public class TestMybatisController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/selectAllUser")
    public List<User> selectAllUser() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        List<User> users = userMapper.selectList(queryWrapper);
        return users;
    }

}