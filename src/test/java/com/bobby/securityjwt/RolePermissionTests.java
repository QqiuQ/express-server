package com.bobby.securityjwt;

import com.bobby.securityjwt.common.RoleConst;
import com.bobby.securityjwt.entity.Permission;
import com.bobby.securityjwt.entity.Role;
import com.bobby.securityjwt.entity.dto.PermissionDto;
import com.bobby.securityjwt.mapper.PermissionMapper;
import com.bobby.securityjwt.mapper.RoleMapper;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @className: PermissionMapperTests
 * @author: Bobby
 * @date: 10/17/2023
 **/
@SpringBootTest
public class RolePermissionTests {
    @Autowired
    RoleMapper roleMapper;
    @Resource
    PermissionMapper permissionMapper;

    @Test
    public void createRoles() {
        // for employee
        Role superAdmin = new Role();
        superAdmin.setName(RoleConst.SUPER_ADMIN);
        superAdmin.setCreateTime(LocalDateTime.now());

        Role stationAdmin = new Role();
        stationAdmin.setName(RoleConst.STATION_ADMIN);
        stationAdmin.setCreateTime(LocalDateTime.now());

        Role deliveryMan = new Role();
        deliveryMan.setName(RoleConst.DELIVERY_MAN);
        deliveryMan.setCreateTime(LocalDateTime.now());

        Role normalEmployee = new Role();
        normalEmployee.setName(RoleConst.NORMAL_EMPLOYEE);
        normalEmployee.setCreateTime(LocalDateTime.now());

        roleMapper.insert(superAdmin);
        roleMapper.insert(stationAdmin);
        roleMapper.insert(deliveryMan);
        roleMapper.insert(normalEmployee);
    }

    /**
     * super_admin 对user有查看、插入、修改、删除权限
     * normal_employee 对user只有查看权限
     */
    @Test
    public void createPermissions() {
        Role superAdmin = roleMapper.getRoleByRoleName(RoleConst.SUPER_ADMIN);
        Role normalEmployee = roleMapper.getRoleByRoleName(RoleConst.NORMAL_EMPLOYEE);
        String domain = "user";
        String[] permissions = {"read", "edit", "add", "delete"};
        for (int i = 0; i < 4; i++) {
            Permission p = new Permission();
            p.setRoleId(superAdmin.getId());
            p.setDomain(domain);
            p.setPermission(permissions[i]);
            p.setCreateTime(LocalDateTime.now());

            permissionMapper.insert(p);
        }

        Permission p = new Permission();
        p.setRoleId(normalEmployee.getId());
        p.setDomain(domain);
        p.setPermission(permissions[0]);
        p.setCreateTime(LocalDateTime.now());

        permissionMapper.insert(p);

    }

    @Test
    public void getSuperAdminPermissions() {
        List<PermissionDto> permissionDtoList = permissionMapper.getPermissionsByRoleName(RoleConst.SUPER_ADMIN);
        Assert.assertNotNull(permissionDtoList);
        Assert.assertFalse(permissionDtoList.isEmpty());
        for (PermissionDto pDto :
                permissionDtoList) {
            System.out.println(pDto.toString());
        }
    }
}
