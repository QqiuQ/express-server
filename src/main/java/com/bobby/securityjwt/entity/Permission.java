package com.bobby.securityjwt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;

/**
 * @className: Permission
 * @author: Bobby
 * @date: 10/17/2023
 **/
@Data
@TableName("permission")
public class Permission implements GrantedAuthority {
    private Integer id;
    private String domain;
    private String permission;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Permission() {
    }

    public Permission(String domain, String permission) {
        this.domain = domain;
        this.permission = permission;
        this.createTime = LocalDateTime.now();
    }

    @Override
    public String getAuthority() {
        return String.format("%s:%s", domain, permission);
    }
}
