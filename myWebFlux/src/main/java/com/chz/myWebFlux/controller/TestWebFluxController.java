package com.chz.myWebFlux.controller;

import com.chz.myWebFlux.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class TestWebFluxController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, WebFlux !";
    }

    @GetMapping("/hello2")
    public Mono<String> hello2() {
        return Mono.just("Hello, WebFlux 2!");
    }

    @GetMapping("/user")
    public Mono<User> getUser() {
        User user = new User();
        user.setName("chz");
        user.setDesc("chz_desc");
        return Mono.just(user);
    }


}

