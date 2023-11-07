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


    Employee selectByCode(String code);

    List<Employee> selectAll();
}
