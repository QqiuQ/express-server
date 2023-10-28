package com.bobby.securityjwt;

import com.bobby.securityjwt.common.RoleConst;
import com.bobby.securityjwt.entity.Role;
import com.bobby.securityjwt.mapper.RoleMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @className: RoleTests
 * @author: Bobby
 * @date: 10/24/2023
 **/
@SpringBootTest
public class RoleTests {
    @Resource
    RoleMapper roleMapper;

    @Test
    void createRoles() {
        String[] roleNames = new String[]{
                RoleConst.USER,
                RoleConst.EMPLOYEE,
                RoleConst.DELIVERY_MAN,
                RoleConst.STATION_ADMIN,
                RoleConst.SUPER_ADMIN
        };
        for (String name :
                roleNames) {
            Role role = new Role();
            role.setName(name);
            role.setCreateTime(LocalDateTime.now());

            if (roleMapper.insert(role) > 0) System.out.println("insert success: " + name);
            else System.out.printf("insert failed !!!!");
        }

    }

    @Test
    void getByRoleName() {
        Role role = roleMapper.getRoleByRoleName(RoleConst.USER);
        System.out.println(role);
    }
}
