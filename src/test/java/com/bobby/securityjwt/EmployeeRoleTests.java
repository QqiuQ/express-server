package com.bobby.securityjwt;

import com.bobby.securityjwt.common.RoleConst;
import com.bobby.securityjwt.entity.Employee;
import com.bobby.securityjwt.entity.EmployeeRole;
import com.bobby.securityjwt.entity.Role;
import com.bobby.securityjwt.mapper.EmployeeMapper;
import com.bobby.securityjwt.mapper.EmployeeRoleMapper;
import com.bobby.securityjwt.mapper.RoleMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @className: EmployeeRoleTests
 * @author: Bobby
 * @date: 10/17/2023
 **/
@SpringBootTest
public class EmployeeRoleTests {
    @Resource
    EmployeeMapper employeeMapper;
    @Resource
    RoleMapper roleMapper;
    @Resource
    EmployeeRoleMapper employeeRoleMapper;

    @Test
    public void addEmployeeRoles() {
        Employee bobby = employeeMapper.selectByUsername("bobby");
        Role superAdmin = roleMapper.getRoleByRoleName(RoleConst.SUPER_ADMIN);

        EmployeeRole er = new EmployeeRole();
        er.setEmployeeId(bobby.getId());
        er.setRoleId(superAdmin.getId());
        er.setCreateTime(LocalDateTime.now());
        employeeRoleMapper.insert(er);

        Employee normalUser = employeeMapper.selectByUsername("normaluser");
        Role normalEmp = roleMapper.getRoleByRoleName(RoleConst.NORMAL_EMPLOYEE);
        EmployeeRole er2 = new EmployeeRole();
        er2.setEmployeeId(normalUser.getId());
        er2.setRoleId(normalEmp.getId());
        er2.setCreateTime(LocalDateTime.now());
        employeeRoleMapper.insert(er2);
    }
}
