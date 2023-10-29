package com.bobby.securityjwt;

import com.bobby.securityjwt.common.AccountConst;
import com.bobby.securityjwt.common.RoleConst;
import com.bobby.securityjwt.entity.Employee;
import com.bobby.securityjwt.entity.Role;
import com.bobby.securityjwt.entity.User;
import com.bobby.securityjwt.entity.UserRole;
import com.bobby.securityjwt.mapper.EmployeeMapper;
import com.bobby.securityjwt.mapper.RoleMapper;
import com.bobby.securityjwt.mapper.UserMapper;
import com.bobby.securityjwt.mapper.UserRoleMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @className: UserRoleTests
 * @author: Bobby
 * @date: 10/29/2023
 **/
@SpringBootTest
public class UserRoleTests {
    @Resource
    UserRoleMapper userRoleMapper;
    @Resource
    EmployeeMapper employeeMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    RoleMapper roleMapper;

    @Test
    void createUserRole() {
        Employee bobby = employeeMapper.selectByUsername("bobby");
        Role superAdmin = roleMapper.getRoleByRoleName(RoleConst.SUPER_ADMIN);
        UserRole ur1 = new UserRole(bobby.getId(), superAdmin.getId(), AccountConst.TYPE_EMPLOYEE);


        Employee normaluser = employeeMapper.selectByUsername("normaluser");
        Role employeeRole = roleMapper.getRoleByRoleName(RoleConst.EMPLOYEE);
        UserRole ur2 = new UserRole(normaluser.getId(), employeeRole.getId(), AccountConst.TYPE_EMPLOYEE);

        User vividbobo = userMapper.selectByUsername("vividbobo");
        Role userRole = roleMapper.getRoleByRoleName(RoleConst.USER);
        UserRole ur3 = new UserRole(vividbobo.getId(), userRole.getId(), AccountConst.TYPE_USER);

        userRoleMapper.insert(ur1);
        userRoleMapper.insert(ur2);
        userRoleMapper.insert(ur3);
    }

    @Test
    void createNewUserRole() {
        Employee bobby = employeeMapper.selectByUsername("bobby");
        Role stationRole = roleMapper.getRoleByRoleName(RoleConst.STATION_ADMIN);
        UserRole ur1 = new UserRole(bobby.getId(), stationRole.getId(), AccountConst.TYPE_EMPLOYEE);
        userRoleMapper.insert(ur1);

    }

    @Test
    void select() {
        Employee bobby = employeeMapper.selectByUsername("bobby");

        List<Role> roleList = userRoleMapper.getRolesByIdAndType(bobby.getId(), AccountConst.TYPE_EMPLOYEE);
        System.out.println(roleList);
    }
}
