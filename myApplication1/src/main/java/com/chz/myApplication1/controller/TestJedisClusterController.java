package com.chz.myApplication1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

@Slf4j
@RestController
@RequestMapping("/jedisCluster")
public class TestJedisClusterController {

    @Autowired(required = false)
    private JedisCluster jedisCluster;

    @GetMapping("/get")
    public String get() {
        String a = jedisCluster.get("a");
        return "get: "+ a;
    }

    @GetMapping("/set")
    public String set() {
        String a = jedisCluster.set("a", "a-1");
        return "set: "+ a;
    }

}