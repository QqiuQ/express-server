package com.bobby.securityjwt.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bobby.securityjwt.common.AjaxResult;
import com.bobby.securityjwt.common.Result;
import com.bobby.securityjwt.entity.Employee;
import com.bobby.securityjwt.entity.User;
import com.bobby.securityjwt.entity.UserRole;
import com.bobby.securityjwt.mapper.UserRoleMapper;
import com.bobby.securityjwt.service.EmployeeService;
import com.bobby.securityjwt.util.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: EmployeeController
 * @author: Bobby
 * @date: 10/14/2023
 **/
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    EmployeeService employeeService;
    @Resource
    JwtUtils jwtUtils;
    @Resource
    UserRoleMapper userRoleMapper;

    @GetMapping()
    public AjaxResult list() {
        List<Employee> employeeList = employeeService.selectAll();
        return AjaxResult.success(employeeList);
    }

}
