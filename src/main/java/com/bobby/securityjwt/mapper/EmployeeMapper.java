package com.bobby.securityjwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobby.securityjwt.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @className: EmployeeMapper
 * @author: Bobby
 * @date: 10/13/2023
 **/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 这里的参数与 mapper.xml里面的 <if test="xxx !=null ..." 和 #{xxx} 一致
     * 或者添加注解: @Param, 指定参数名称
     *
     * @param code
     * @return
     */
    Employee selectByCode(@Param("code") String code);

    @Select("select * from employee where username=#{username}")
    Employee selectByUsername(String username);
}
