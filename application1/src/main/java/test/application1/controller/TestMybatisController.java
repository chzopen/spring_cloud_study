package test.application1.controller;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.application1.persistence.po.User;
import test.application1.persistence.mapper.UserMapper;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/mybatis")
public class TestMybatisController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/addUser")
    public String addUser() {
        User user = new User();
        user.setId(1L);
        user.setName("user1");
        user.setSex("male");
        user.setAge(16);
        user.setEmail("dddd");
        int result = userMapper.insert(user);
        return "addUser: "+ result;
    }

    @GetMapping("/getUser")
    public String getUser() {
        User user = userMapper.selectById(1L);
        return "addUser: "+ JSON.toJSONString(user);
    }

    @GetMapping("/selectUserById")
    public String selectUserById() {
        User user = userMapper.selectUserById(1L);
        return "addUser: "+ JSON.toJSONString(user);
    }

}