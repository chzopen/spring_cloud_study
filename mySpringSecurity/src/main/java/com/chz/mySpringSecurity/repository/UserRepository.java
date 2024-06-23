package com.chz.mySpringSecurity.repository;

import com.chz.mySpringSecurity.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {

    // 这个用于模拟数据库里面的可登录用户的信息
    public static ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    static {
        //
        User user = new User();
        user.setUserName("user");
        user.setPassword(new BCryptPasswordEncoder().encode("123"));
        users.put(user.getUserName(), user);
        //
        User admin = new User();
        admin.setUserName("admin");
        admin.setPassword(new BCryptPasswordEncoder().encode("456"));
        users.put(admin.getUserName(), admin);
    }


}
