package com.bobby.securityjwt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @className: RolePermission
 * @author: Bobby
 * @date: 10/24/2023
 **/
@Deprecated()
@Data
@TableName("role_permission")
public class RolePermission {
    private Long id;
    private Integer roleId;
    private Integer permissionId;

    public RolePermission() {
    }

    public RolePermission(Integer roleId, Integer permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }
}
