package com.bobby.securityjwt.service;

import com.bobby.securityjwt.config.security.userdetails.EmployeeDetails;
import com.bobby.securityjwt.config.security.userdetails.MyUserDetails;
import org.springframework.stereotype.Component;

/**
 * @className: SecurityService
 * @author: Bobby
 * @date: 10/17/2023
 * 与Security UserDetailService 相关的操作
 **/

public interface SecurityService {

    EmployeeDetails getEmployeeDetails(String username);

    MyUserDetails getMyUserDetails(String username);
}
