package com.bobby.securityjwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobby.securityjwt.entity.Permission;
import com.bobby.securityjwt.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @className: RolePermissionMapper
 * @author: Bobby
 * @date: 10/24/2023
 **/
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    List<Permission> getPermissionsByRoleId(Integer roleId);
}
