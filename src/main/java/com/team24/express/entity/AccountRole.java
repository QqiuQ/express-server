package com.team24.express.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @className: UserRole
 * @author: Bobby
 * @date: 10/29/2023
 * 用户与角色关系表
 **/
@Schema(name = "AccountRole", title = "账户与角色关系表", description = "账户与角色关系类属性")
@Data
@TableName("account_role")
public class AccountRole {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long accountId;
    private Integer roleId;
    private String type;    // 员工还是普通用户

    public AccountRole(Long accountId, Integer roleId, String type) {
        this.accountId = accountId;
        this.roleId = roleId;
        this.type = type;
    }
}
