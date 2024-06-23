package com.chz.mySpringSecurity.config;

import com.chz.mySpringSecurity.filter.AccessTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AccessTokenFilter accessTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                //访问"/"和"/home"路径的请求都允许
                .antMatchers(
                        "/",            // 这个会跳转到主页，要放过
                        "/home",           // 这是主页的地址，要放过
                        "/security/login2"          // 这个是提交登录的地址，要放过
                )
                .permitAll()
                //而其他的请求都需要认证
                .anyRequest()
                .authenticated()
                .and()
                //修改Spring Security默认的登陆界面
                .formLogin()
                .loginPage("/security/login")       // 登录地址是【/security/login】
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http.addFilterBefore(accessTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();     // 这个会对用户提交的密码进行编码，然后才跟密码库里面的密码进行比较
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
