package com.bobby.securityjwt.service;

import com.bobby.securityjwt.entity.Permission;

import java.util.List;

/**
 * @className: RolePermission
 * @author: Bobby
 * @date: 10/24/2023
 **/
public interface RolePermissionService {
    List<Permission> getPermissionsByRoleId(Integer roleId);
}
