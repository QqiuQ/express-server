package com.bobby.securityjwt.config.security.userdetails;

import com.bobby.securityjwt.service.SecurityService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @className: EmployeeDetailService
 * @author: Bobby
 * @date: 10/13/2023
 **/
@Component
public class EmployeeDetailService implements UserDetailsService {
    @Resource
    SecurityService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return service.getEmployeeDetails(username);
    }
}
