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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Controller
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello(){
        log.info("chz >>> SecurityController.hello(): ");
        return "this is hello page!";
    }
 
    @GetMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        log.info("chz >>> SecurityController.login(): ");
        return "/login.html";
    }

    @GetMapping(value = "/login2")
    @ResponseBody
    public ResponseResult login2(@RequestParam String username, @RequestParam String password)
    {
        log.info("chz >>> SecurityController.login2(): username={}, password={}", username, password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);      // 这个会触发【UserDetailsService.loadUserByUsername(String username)】方法被调用
        if(Objects.isNull(authenticate)){
            log.info("chz >>> SecurityController.login2(): 用户名或密码错误");
            throw new RuntimeException("用户名或密码错误");
        }

        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        loginUser.setRefreshToken(Math.abs(ThreadLocalRandom.current().nextLong())+"");
        loginUser.setAccessToken(Math.abs(ThreadLocalRandom.current().nextLong())+"");
        LoginUsers.users.put(loginUser.getAccessToken(), loginUser);
        log.info("chz >>> accessToken: " + loginUser.getAccessToken());

        // context里面设置了【authenticationToken】就表示用户已经登录了，但是这个是根据cookie里面有sessionId判断的，跟accessToken无关
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return new ResponseResult(200,"登陆成功", loginUser.getAccessToken());
    }

    @GetMapping(value = "/logout2")
    @ResponseBody
    public ResponseResult logout2(@RequestParam String accessToken)
    {
        log.info("chz >>> SecurityController.login2(): accessToken={}", accessToken);
        LoginUsers.users.remove(accessToken);

        // context里面清除了【authenticationToken】就表示用户已经退出登录了，但是这个是根据cookie里面有sessionId判断的，跟accessToken无关
        SecurityContextHolder.getContext().setAuthentication(null);

        return new ResponseResult(200,"退出成功");
    }

}