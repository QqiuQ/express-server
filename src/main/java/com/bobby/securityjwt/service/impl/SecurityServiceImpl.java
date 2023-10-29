package com.bobby.securityjwt.service.impl;

import com.bobby.securityjwt.common.AccountConst;
import com.bobby.securityjwt.config.security.userdetails.AccountDetails;
import com.bobby.securityjwt.entity.Employee;
import com.bobby.securityjwt.entity.Role;
import com.bobby.securityjwt.entity.User;
import com.bobby.securityjwt.mapper.EmployeeMapper;
import com.bobby.securityjwt.mapper.UserMapper;
import com.bobby.securityjwt.mapper.UserRoleMapper;
import com.bobby.securityjwt.service.SecurityService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: SecurityServiceImpl
 * @author: Bobby
 * @date: 10/17/2023
 **/
@Service
public class SecurityServiceImpl implements SecurityService {
    @Resource
    EmployeeMapper employeeMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    UserRoleMapper userRoleMapper;

    /**
     * @param username
     * @return
     */
    @Override
    public AccountDetails getEmployeeDetails(String username) {
        Employee employee = employeeMapper.selectByUsername(username);
        List<Role> roleList = userRoleMapper.getRolesByIdAndType(employee.getId(), AccountConst.TYPE_EMPLOYEE);
        return new AccountDetails(employee, roleList, AccountConst.TYPE_EMPLOYEE);
    }

    /**
     * @param username
     * @return
     */
    @Override
    public AccountDetails getUserDetails(String username) {
        User user = userMapper.selectByUsername(username);
        List<Role> roleList = userRoleMapper.getRolesByIdAndType(user.getId(), AccountConst.TYPE_USER);
        return new AccountDetails(user, roleList, AccountConst.TYPE_USER);
    }
}
