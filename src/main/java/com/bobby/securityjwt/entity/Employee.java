package com.bobby.securityjwt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @className: Employee
 * @author: Bobby
 * @date: 10/13/2023
 **/
@Data
@TableName("employee")
public class Employee implements UserDetails {  // 由于要实现security登录认证
    private Long id;
    private String code;
    private String name;
    private Integer sex;
    private String avatar;
    private String phone;
    private String username;
    private String password;
    private String email;
    private Integer level;
    private Long roleId;    // foreign key
    private Integer status;
    private String address;
    private Integer accountStatus;
    private LocalDate hireDate;
    private LocalDateTime lastLoginTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 根据foreign key: roleId 找到的角色集合
        // 下面先自定义测试
        String role = "ROLE_SUPER_ADMIN";   //超级管理员
        GrantedAuthority authority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        };
        ArrayList<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
        roleList.add(authority);
        return roleList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountStatus == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
