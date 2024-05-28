package com.chz.application1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@Slf4j
@RestController
@RequestMapping("/jedis")
public class TestJedisController {

    @Autowired(required = false)
    private Jedis jedis;

    @GetMapping("/get")
    public String get() {
        String a = jedis.get("a");
        return "get: "+ a;
    }

    @GetMapping("/set")
    public String set() {
        String a = jedis.set("a", "a-1");
        return "set: "+ a;
    }

}