package com.bobby.securityjwt.service;

import com.bobby.securityjwt.config.security.userdetails.AccountDetails;

/**
 * @className: SecurityService
 * @author: Bobby
 * @date: 10/17/2023
 * 与Security UserDetailService 相关的操作
 **/

public interface SecurityService {

    AccountDetails getEmployeeDetails(String username);

    AccountDetails getUserDetails(String username);
}
