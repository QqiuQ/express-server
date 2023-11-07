package com.team24.express;

import com.team24.express.common.AccountConst;
import com.team24.express.common.RoleConst;
import com.team24.express.entity.Employee;
import com.team24.express.entity.Role;
import com.team24.express.entity.User;
import com.team24.express.entity.AccountRole;
import com.team24.express.mapper.EmployeeMapper;
import com.team24.express.mapper.RoleMapper;
import com.team24.express.mapper.UserMapper;
import com.team24.express.mapper.AccountRoleMapper;
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
public class AccountRoleTests {
    @Resource
    AccountRoleMapper accountRoleMapper;
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
        AccountRole ur1 = new AccountRole(bobby.getId(), superAdmin.getId(), AccountConst.TYPE_EMPLOYEE);


        Employee normaluser = employeeMapper.selectByUsername("normaluser");
        Role employeeRole = roleMapper.getRoleByRoleName(RoleConst.EMPLOYEE);
        AccountRole ur2 = new AccountRole(normaluser.getId(), employeeRole.getId(), AccountConst.TYPE_EMPLOYEE);

        User vividbobo = userMapper.selectByUsername("vividbobo");
        Role userRole = roleMapper.getRoleByRoleName(RoleConst.USER);
        AccountRole ur3 = new AccountRole(vividbobo.getId(), userRole.getId(), AccountConst.TYPE_USER);

        accountRoleMapper.insert(ur1);
        accountRoleMapper.insert(ur2);
        accountRoleMapper.insert(ur3);
    }

    @Test
    void createNewUserRole() {
        Employee bobby = employeeMapper.selectByUsername("bobby");
        Role stationRole = roleMapper.getRoleByRoleName(RoleConst.STATION_ADMIN);
        AccountRole ur1 = new AccountRole(bobby.getId(), stationRole.getId(), AccountConst.TYPE_EMPLOYEE);
        accountRoleMapper.insert(ur1);

    }

    @Test
    void select() {
        Employee bobby = employeeMapper.selectByUsername("bobby");

        List<Role> roleList = accountRoleMapper.getRolesByIdAndType(bobby.getId(), AccountConst.TYPE_EMPLOYEE);
        System.out.println(roleList);
    }
}
