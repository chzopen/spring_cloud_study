package com.chz.mySpringSecurity.filter;

import com.chz.mySpringSecurity.entity.LoginUser;
import com.chz.mySpringSecurity.entity.LoginUsers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AccessTokenFilter extends OncePerRequestFilter
{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        log.info("chz >>> ChzAuthenticationTokenFilter.doFilterInternal(): uri:{}, queryParam={}", request.getRequestURI(), request.getQueryString());
        LoginUser loginUser = null;
        String accessToken = request.getParameter("accessToken");
        if( !StringUtils.isEmpty(accessToken) ) {
            loginUser = StringUtils.isEmpty(accessToken) ? null : LoginUsers.users.get(accessToken);
        }

        if( loginUser!=null ){
            // 这是登录过的，可以访问受限资源
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, loginUser.getPassword(), loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }else {
            // 没有accessToken清空掉authentication，不让访问受限资源
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        // 不管有没有登录，后面的【UsernamePasswordAuthenticationFilter】会进行权限判断，也直接放过
        filterChain.doFilter(request, response);
    }
}