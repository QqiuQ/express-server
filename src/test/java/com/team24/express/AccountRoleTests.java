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
import com.team24.express.service.EmployeeService;
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
    @Resource
    EmployeeService employeeService;

    @Test
    void createDefaultEmployee() {
        Employee bobby = new Employee();
        bobby.setUsername("bobby");
        bobby.setCode("000001");
        bobby.setPassword("123456");
        try {
            employeeService.addSpecifyRoleEmployee(bobby, RoleConst.SUPER_ADMIN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Employee station = new Employee();
        station.setUsername("station");
        station.setCode("000002");
        station.setPassword("123456");
        try {
            employeeService.addSpecifyRoleEmployee(station, RoleConst.STATION_ADMIN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Employee deliveryman = new Employee();
        deliveryman.setUsername("deliveryman");
        deliveryman.setPassword("123456");
        deliveryman.setCode("000003");
        try {
            employeeService.addSpecifyRoleEmployee(deliveryman, RoleConst.DELIVERY_MAN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Employee admin = new Employee();
        admin.setUsername("admin");
        admin.setCode("000004");
        admin.setPassword("123456");
        try {
            employeeService.addSpecifyRoleEmployee(admin, RoleConst.SUPER_ADMIN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Employee employee = new Employee();
        employee.setUsername("employee");
        employee.setPassword("123456");
        employee.setCode("000005");
        try {
            employeeService.addSpecifyRoleEmployee(employee, RoleConst.EMPLOYEE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void createEmployees() {
        for (int i = 0; i < 20; i++) {
            Employee employee = new Employee();
            employee.setUsername("emp#" + i);
            employee.setCode(String.format("emp%05d", i));
            employee.setPassword("123456");
            employeeService.add(employee);
        }
    }

}
