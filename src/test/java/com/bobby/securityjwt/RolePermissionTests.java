package com.bobby.securityjwt;

import com.bobby.securityjwt.common.RoleConst;
import com.bobby.securityjwt.entity.Permission;
import com.bobby.securityjwt.entity.Role;
import com.bobby.securityjwt.entity.RolePermission;
import com.bobby.securityjwt.mapper.PermissionMapper;
import com.bobby.securityjwt.mapper.RoleMapper;
import com.bobby.securityjwt.mapper.RolePermissionMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @className: RolePermissionTests
 * @author: Bobby
 * @date: 10/24/2023
 **/
@SpringBootTest
public class RolePermissionTests {
    @Resource
    RoleMapper roleMapper;
    @Resource
    PermissionMapper permissionMapper;
    @Resource
    RolePermissionMapper rolePermissionMapper;

    @Test
    void assignSuperAdminPermissions() {
        Role superAdmin = roleMapper.getRoleByRoleName(RoleConst.SUPER_ADMIN);
        List<Permission> actions = permissionMapper.getByDomain("user");
        for (Permission action : actions) {
            RolePermission rolePermission = new RolePermission(superAdmin.getId(), action.getId());
            rolePermissionMapper.insert(rolePermission);
        }
    }

    @Test
    void assignEmployeePermissions() {
        Role normalEmployee = roleMapper.getRoleByRoleName(RoleConst.EMPLOYEE);
        Permission action = permissionMapper.getByDomainAndPermission("user", "list");
        if (action != null) {
            RolePermission rolePermission = new RolePermission(normalEmployee.getId(), action.getId());
            rolePermissionMapper.insert(rolePermission);
        } else {
            System.out.println("can't find the permission..");
        }
    }

    @Test
    void getPermissionsByRoleId() {
        Role superAdmin = roleMapper.getRoleByRoleName(RoleConst.SUPER_ADMIN);
        List<Permission> permissions = rolePermissionMapper.getPermissionsByRoleId(superAdmin.getId());
        for (Permission a :
                permissions) {
            System.out.println(a.getAuthority());
        }
    }
}
