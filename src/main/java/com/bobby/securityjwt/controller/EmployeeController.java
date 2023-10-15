package com.bobby.securityjwt.controller;

import com.bobby.securityjwt.common.AjaxResult;
import com.bobby.securityjwt.entity.Employee;
import com.bobby.securityjwt.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: EmployeeController
 * @author: Bobby
 * @date: 10/14/2023
 **/
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    EmployeeService employeeService;

    @GetMapping()
    @PreAuthorize("hasPermission('#user','list')")
    public AjaxResult list() {
        List<Employee> employeeList = employeeService.selectAll();
        return AjaxResult.success(employeeList);
    }
}
