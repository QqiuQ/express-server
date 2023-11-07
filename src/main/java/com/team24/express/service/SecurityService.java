package com.team24.express.service;

import com.team24.express.config.security.userdetails.AccountDetails;

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
