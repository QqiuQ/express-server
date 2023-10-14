package com.bobby.securityjwt.service;

import com.bobby.securityjwt.entity.Employee;

import java.util.List;

/**
 * @className: EmployeeService
 * @author: Bobby
 * @date: 10/13/2023
 **/

public interface EmployeeService {
    Employee selectByUsername(String username);

    Employee selectByCode(String code);

    List<Employee> selectAll();
}
