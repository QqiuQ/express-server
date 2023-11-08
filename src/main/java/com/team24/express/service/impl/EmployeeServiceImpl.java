package com.team24.express.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team24.express.entity.Account;
import com.team24.express.entity.Employee;
import com.team24.express.entity.User;
import com.team24.express.mapper.EmployeeMapper;
import com.team24.express.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @className: EmployeeServiceImpl
 * @author: Bobby
 * @date: 10/13/2023
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    EmployeeMapper employeeMapper;

    /**
     * QueryWrapper 写法
     */
    @Override
    public Employee selectByUsername(String username) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return employeeMapper.selectOne(wrapper);   // 找到具体的一个
    }

    @Override
    public Employee selectById(Long id) {
        return employeeMapper.selectById(id);
    }

    @Override
    public List<Employee> selectEmployeeList(Employee employee) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        if (Objects.isNull(employee)) {
            return employeeMapper.selectList(wrapper);
        }
        // 判断
        if (employee.getUsername() != null && !employee.getUsername().isEmpty()) {
            wrapper.like("username", employee.getUsername());
        }
        if (employee.getEmail() != null && !employee.getEmail().isEmpty()) {
            wrapper.like("email", employee.getEmail());
        }
        if (employee.getAccountStatus() != null) {
            wrapper.eq("accountStatus", employee.getAccountStatus());
        }
        if (employee.getCode() != null && !employee.getCode().isEmpty()) {
            wrapper.eq("code", employee.getCode());
        }
        if (employee.getName() != null && !employee.getName().isEmpty()) {
            wrapper.like("name", employee.getName());
        }
        if (employee.getSex() != null) {
            wrapper.eq("sex", employee.getSex());
        }
        if (employee.getPhone() != null && !employee.getPhone().isEmpty()) {
            wrapper.like("phone", employee.getPhone());
        }
        if (employee.getStatus() != null) {
            wrapper.eq("status", employee.getStatus());
        }
        if (employee.getAddress() != null && !employee.getAddress().isEmpty()) {
            wrapper.eq("address", employee.getAddress());
        }
        if (employee.getHireDate() != null) {
            wrapper.ge("hireDate", employee.getAddress());
        }

        return employeeMapper.selectList(wrapper);
    }

    @Override
    public Boolean add(Employee employee) {
        if (employeeMapper.insert(employee) > 0) return true;
        return false;
    }

    @Override
    public Boolean edit(Employee employee) {
        if (employeeMapper.updateById(employee) > 0) return true;
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        if (employeeMapper.deleteById(id) > 0) return true;
        return false;
    }

    @Override
    public Boolean updateLastLoginTime(Employee employee) {
        employee.setLastLoginTime(LocalDateTime.now());
        if (employeeMapper.updateById((Employee) employee) > 0) return true;
        return false;
    }

}
