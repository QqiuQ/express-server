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

    Boolean add(Employee employee);

    Boolean edit(Employee employee);

    Boolean delete(Long id);

    Boolean updateLastLoginTime(Employee employee);
}
