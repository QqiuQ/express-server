package com.bobby.securityjwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobby.securityjwt.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: RoleMapper
 * @author: Bobby
 * @date: 10/17/2023
 **/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    Role getRoleByRoleName(String name);
}
