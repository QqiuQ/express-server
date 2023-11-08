package com.team24.express.controller;

import com.team24.express.common.Result;
import com.team24.express.entity.Employee;
import com.team24.express.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: EmployeeController
 * @author: Bobby
 * @date: 10/14/2023
 **/
@Tag(name = "EmployeeController")
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    EmployeeService employeeService;

    @GetMapping()
    public Result list() {
        List<Employee> employeeList = employeeService.selectAll();
        Result result = Result.success();
        result.setData(employeeList);
        return result;
    }


}
