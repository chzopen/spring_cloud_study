package com.chz.myJWT.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        request.getSession().setAttribute("user", "Ljj");
        return "Login Success";
    }

    @GetMapping("/getUser")
    public String getUser(HttpServletRequest request) {
        String user = (String) request.getSession().getAttribute("user");
        return user;
    }

}