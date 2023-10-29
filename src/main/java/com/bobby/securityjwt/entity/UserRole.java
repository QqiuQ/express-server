package com.bobby.securityjwt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @className: UserRole
 * @author: Bobby
 * @date: 10/29/2023
 * 用户与角色关系表
 **/

@Data
@TableName("user_role")
public class UserRole {
//    private Long id;
    private Long userId;
    private Integer roleId;
    private String type;    // 员工还是普通用户

    public UserRole(Long userId, Integer roleId, String type) {
        this.userId = userId;
        this.roleId = roleId;
        this.type = type;
    }
}
