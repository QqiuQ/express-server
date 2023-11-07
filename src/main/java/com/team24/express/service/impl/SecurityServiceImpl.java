package com.team24.express.service.impl;

import com.team24.express.common.AccountConst;
import com.team24.express.config.security.userdetails.AccountDetails;
import com.team24.express.entity.Employee;
import com.team24.express.entity.Role;
import com.team24.express.entity.User;
import com.team24.express.mapper.EmployeeMapper;
import com.team24.express.mapper.UserMapper;
import com.team24.express.mapper.AccountRoleMapper;
import com.team24.express.service.SecurityService;
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
    AccountRoleMapper accountRoleMapper;

    /**
     * @param username
     * @return
     */
    @Override
    public AccountDetails getEmployeeDetails(String username) {
        Employee employee = employeeMapper.selectByUsername(username);
        List<Role> roleList = accountRoleMapper.getRolesByIdAndType(employee.getId(), AccountConst.TYPE_EMPLOYEE);
        return new AccountDetails(employee, roleList, AccountConst.TYPE_EMPLOYEE);
    }

    /**
     * @param username
     * @return
     */
    @Override
    public AccountDetails getUserDetails(String username) {
        User user = userMapper.selectByUsername(username);
        List<Role> roleList = accountRoleMapper.getRolesByIdAndType(user.getId(), AccountConst.TYPE_USER);
        return new AccountDetails(user, roleList, AccountConst.TYPE_USER);
    }
}
