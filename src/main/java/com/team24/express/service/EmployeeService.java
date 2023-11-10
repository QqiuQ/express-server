package com.team24.express.service;

import com.team24.express.entity.Employee;

import java.util.List;

/**
 * @className: EmployeeService
 * @author: Bobby
 * @date: 10/13/2023
 **/

public interface EmployeeService {
    Employee selectByUsername(String username);

    Employee selectById(Long id);

    List<Employee> selectEmployeeList(Employee employee);

    /**
     * 包含密码加密和添加普通员工角色关联
     *
     * @param employee
     * @return
     */
    Boolean add(Employee employee);

    Boolean edit(Employee employee);

    Boolean delete(Long id);

    Boolean updateLastLoginTime(Employee employee);

    Boolean addSpecifyRoleEmployee(Employee employee, String roleName);

    List<Employee> selectRoleEmployees(String roleName);
}
