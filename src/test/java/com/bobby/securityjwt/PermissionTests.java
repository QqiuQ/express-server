package com.bobby.securityjwt;

import com.bobby.securityjwt.entity.Permission;
import com.bobby.securityjwt.mapper.PermissionMapper;
import com.bobby.securityjwt.service.impl.PermissionServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @className: PermissionTests
 * @author: Bobby
 * @date: 10/24/2023
 **/
@SpringBootTest
public class PermissionTests {
    @Resource
    PermissionMapper permissionMapper;

    @Test
    void createPermission() {
        String domain = "user";
        String[] actions = new String[]{"list", "edit", "insert", "delete"};
        for (String action :
                actions) {
            Permission permission = new Permission(domain, action);
            permissionMapper.insert(permission);
        }
    }

    @Test
    void getByDomain() {
        String domain = "user";
        List<Permission> actions = permissionMapper.getByDomain(domain);
        for (Permission p :
                actions) {
            System.out.println(p);
        }
    }

}
