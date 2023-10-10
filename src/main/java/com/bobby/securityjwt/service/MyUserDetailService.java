package com.bobby.securityjwt.service;

import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @className: UserDetailService
 * @author: Bobby
 * @date: 10/10/2023
 **/
@Component
public class MyUserDetailService implements UserDetailsService {
    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.selectByUsername(username);
    }
}
