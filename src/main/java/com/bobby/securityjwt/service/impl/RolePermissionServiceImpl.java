package com.bobby.securityjwt.service.impl;

import com.bobby.securityjwt.entity.Permission;
import com.bobby.securityjwt.mapper.RolePermissionMapper;
import com.bobby.securityjwt.service.RolePermissionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: RolePermissionServiceImpl
 * @author: Bobby
 * @date: 10/24/2023
 **/
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Resource
    RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Permission> getPermissionsByRoleId(Integer roleId) {
        return rolePermissionMapper.getPermissionsByRoleId(roleId);
    }
}
