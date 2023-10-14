package com.bobby.securityjwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bobby.securityjwt.entity.Employee;
import com.bobby.securityjwt.mapper.EmployeeMapper;
import com.bobby.securityjwt.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 利用传统 *Mapper.xml 的写法
     */
    @Override
    public Employee selectByCode(String code) {
        return employeeMapper.selectByCode(code);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectList(new QueryWrapper<>());
    }
}
