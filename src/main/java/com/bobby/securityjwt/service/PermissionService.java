package com.bobby.securityjwt.service;

import com.bobby.securityjwt.entity.Permission;
import com.bobby.securityjwt.entity.dto.PermissionDto;

import java.util.List;

/**
 * @className: PermissionService
 * @author: Bobby
 * @date: 10/17/2023
 **/
public interface PermissionService {

    List<PermissionDto> getPermissionsByRoleName(String role);

    List<PermissionDto> getPermissionByRoleId(Integer roleId);
}
