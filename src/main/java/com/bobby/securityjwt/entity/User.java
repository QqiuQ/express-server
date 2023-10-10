package com.bobby.securityjwt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bobby.securityjwt.common.Const;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @className: User
 * @author: Bobby
 * @date: 10/8/2023
 * @description: 普通用户类
 **/
@Data
@TableName("user")
public class User implements UserDetails, Serializable {
    private Long id;
    private String nickname;
    private Integer sex;
    private String phone;
    private String username;
    private String password;
    private String email;
    private LocalDate birthday;
    private LocalDateTime register_time;
    private String avatar;
    private Integer account_status;
    private LocalDateTime last_login_time;
    private LocalDateTime create_time;
    private LocalDateTime update_time;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /**
         * 或者从数据库中获取 role,再进行相应处理
         */
        GrantedAuthority role = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_"+Const.ROLE_DEFAULT;  // SecurityConfiguration 里的 hasAnyRole会给角色名前自动加上"ROLE_"
            }
        };
        ArrayList<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
        roleList.add(role);
        return roleList;
    }

    /**
     * 如果有其他字段，以下作相应处理
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
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
