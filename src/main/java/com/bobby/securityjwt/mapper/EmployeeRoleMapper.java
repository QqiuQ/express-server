package com.bobby.securityjwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobby.securityjwt.entity.EmployeeRole;
import com.bobby.securityjwt.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @className: EmployeeRoleMapper
 * @author: Bobby
 * @date: 10/17/2023
 **/
@Mapper
public interface EmployeeRoleMapper extends BaseMapper<EmployeeRole> {

    List<Role> getRolesByEmployeeId(Long id);
}
