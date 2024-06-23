package com.chz.mySpringSecurity.service;

import com.chz.mySpringSecurity.entity.LoginUser;
import com.chz.mySpringSecurity.entity.User;
import com.chz.mySpringSecurity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        log.info("chz >>> UserDetailsServiceImpl.loadUserByUsername(): username={}", username);
        User user = UserRepository.users.get(username);
        if( user==null ){
            throw new UsernameNotFoundException("用户名不存在");
        }

        LoginUser loginUser = new LoginUser(user);
        loginUser.addAuthority("chz_role1");
        return loginUser;
    }
}