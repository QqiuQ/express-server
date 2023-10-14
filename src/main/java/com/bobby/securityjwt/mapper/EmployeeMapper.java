package com.bobby.securityjwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobby.securityjwt.entity.Employee;
import com.bobby.securityjwt.service.EmployeeService;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: EmployeeMapper
 * @author: Bobby
 * @date: 10/13/2023
 **/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    Employee selectByCode(String code);
}
