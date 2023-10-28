package com.bobby.securityjwt;

import com.bobby.securityjwt.common.RoleConst;
import com.bobby.securityjwt.entity.Employee;
import com.bobby.securityjwt.entity.Role;
import com.bobby.securityjwt.mapper.EmployeeMapper;
import com.bobby.securityjwt.mapper.RoleMapper;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

/**
 * @className: EmployeeMapperTests
 * @author: Bobby
 * @date: 10/13/2023
 **/
@SpringBootTest
public class EmployeeTests {
    @Resource
    EmployeeMapper mapper;
    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    RoleMapper roleMapper;

    @Test
    public void insertSuperAdmin() {

        Employee employee = new Employee();
        employee.setCode("sf8848");
        employee.setUsername("bobby");
        employee.setPassword(passwordEncoder.encode("123456"));
        employee.setName("黄准备");
        employee.setAccountStatus(0);   // normal
        Role role = roleMapper.getRoleByRoleName(RoleConst.SUPER_ADMIN);
        employee.setRoleId(role.getId());
        employee.setCreateTime(LocalDateTime.now());
        org.junit.Assert.assertTrue(mapper.insert(employee) > 0);

    }

    @Test
    public void insertNormalEmployee() {
        Employee employee = new Employee();
        employee.setCode("norm123");
        employee.setUsername("normaluser");
        employee.setPassword(passwordEncoder.encode("123456"));
        employee.setName("普通员工");
        employee.setAccountStatus(0);
        Role role = roleMapper.getRoleByRoleName(RoleConst.EMPLOYEE);
        employee.setRoleId(role.getId());
        employee.setCreateTime(LocalDateTime.now());
        Assert.assertTrue(mapper.insert(employee) > 0);
    }

    @Test
    public void selectByCode() {
        String code = "sf8848";
        Employee employee = mapper.selectByCode(code);
        org.junit.Assert.assertNotNull(employee);
        System.out.println(employee);
    }
}
