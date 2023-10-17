package com.bobby.securityjwt.service.impl;

import com.bobby.securityjwt.common.RoleConst;
import com.bobby.securityjwt.config.security.userdetails.EmployeeDetails;
import com.bobby.securityjwt.config.security.userdetails.MyUserDetails;
import com.bobby.securityjwt.entity.Employee;
import com.bobby.securityjwt.entity.Role;
import com.bobby.securityjwt.entity.User;
import com.bobby.securityjwt.mapper.EmployeeMapper;
import com.bobby.securityjwt.mapper.EmployeeRoleMapper;
import com.bobby.securityjwt.mapper.RoleMapper;
import com.bobby.securityjwt.mapper.UserMapper;
import com.bobby.securityjwt.service.SecurityService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    RoleMapper roleMapper;
    @Resource
    EmployeeRoleMapper employeeRoleMapper;

    /**
     * 2. 而员工可以拥有多种角色，例如一个员工他同时是超级管理员又是站点管理员，因此员工的角色从，员工-角色关系表中读取。
     *
     * @param username
     * @return
     */
    @Override
    public EmployeeDetails getEmployeeDetails(String username) {
        Employee employee = employeeMapper.selectByUsername(username);
        List<Role> roleList = employeeRoleMapper.getRolesByEmployeeId(employee.getId());
        EmployeeDetails employeeDetails = new EmployeeDetails(employee, roleList);
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
        Role userRole = roleMapper.getRoleByRoleName(RoleConst.USER);
        List<Role> roleList = new ArrayList<>();
        roleList.add(userRole);
        return new MyUserDetails(user, roleList);
    }
}
