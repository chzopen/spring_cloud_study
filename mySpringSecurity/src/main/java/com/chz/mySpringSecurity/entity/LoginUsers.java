package com.chz.mySpringSecurity.entity;

import java.util.concurrent.ConcurrentHashMap;

public class LoginUsers
{

    // 这个map模拟的是分布式缓存redis的数据，代表已登录的用户列表
    public static ConcurrentHashMap<String, LoginUser> users = new ConcurrentHashMap<>();

}
