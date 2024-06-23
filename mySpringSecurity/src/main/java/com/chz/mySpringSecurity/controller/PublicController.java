package com.chz.mySpringSecurity.controller;

import com.chz.mySpringSecurity.entity.LoginUser;
import com.chz.mySpringSecurity.entity.LoginUsers;
import com.chz.mySpringSecurity.entity.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Controller
@RequestMapping("/")
public class PublicController {

    @GetMapping(value = {"/home","/"})
    @ResponseBody
    public String home(){
        log.info("chz >>> SecurityController.home(): ");
        return "this is home page!";
    }

 
}