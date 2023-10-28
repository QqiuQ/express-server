package com.bobby.securityjwt.service.impl;

import com.bobby.securityjwt.config.security.userdetails.EmployeeDetails;
import com.bobby.securityjwt.config.security.userdetails.MyUserDetails;
import com.bobby.securityjwt.entity.Employee;
import com.bobby.securityjwt.entity.Permission;
import com.bobby.securityjwt.entity.User;
import com.bobby.securityjwt.mapper.EmployeeMapper;
import com.bobby.securityjwt.mapper.RolePermissionMapper;
import com.bobby.securityjwt.mapper.UserMapper;
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
    RolePermissionMapper rolePermissionMapper;

    /**
     * 2. 而员工可以拥有多种角色，例如一个员工他同时是超级管理员又是站点管理员，因此员工的角色从，员工-角色关系表中读取。
     *
     * @param username
     * @return
     */
    @Override
    public EmployeeDetails getEmployeeDetails(String username) {
        Employee employee = employeeMapper.selectByUsername(username);
        List<Permission> authorities = rolePermissionMapper.getPermissionsByRoleId(employee.getRoleId());
        EmployeeDetails employeeDetails = new EmployeeDetails(employee, authorities);
        return employeeDetails;
    }

    /**
     * 1. 由于本系统希望，只对员工进行角色分层，对用户只拥有一种角色----ROLE_USER (普通用户角色)，因此固定获取ROLE_USER。
     *
     * @param username
     * @return
     */
    @Override
    public MyUserDetails getMyUserDetails(String username) {
        User user = userMapper.selectByUsername(username);
        List<Permission> authorities = rolePermissionMapper.getPermissionsByRoleId(user.getRoleId());
        return new MyUserDetails(user, authorities);
    }
}
