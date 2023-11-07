package com.team24.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team24.express.entity.Role;
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
