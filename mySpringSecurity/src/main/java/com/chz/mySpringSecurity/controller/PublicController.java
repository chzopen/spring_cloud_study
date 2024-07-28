package com.chz.mySpringSecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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