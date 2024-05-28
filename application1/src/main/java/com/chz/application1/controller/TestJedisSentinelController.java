package com.chz.application1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

@Slf4j
@RestController
@RequestMapping("/jedisSentinel")
public class TestJedisSentinelController {

    @Autowired(required = false)
    private JedisSentinelPool jedisSentinelPool;

    @GetMapping("/get")
    public String get() {
        try( Jedis jedis = jedisSentinelPool.getResource(); ){
            String a = jedis.get("a");
            return "get: "+ a;
        }
    }

    @GetMapping("/set")
    public String set() {
        try( Jedis jedis = jedisSentinelPool.getResource(); ){
            String a = jedis.set("a", "a-1");
            return "set: "+ a;
        }
    }

}