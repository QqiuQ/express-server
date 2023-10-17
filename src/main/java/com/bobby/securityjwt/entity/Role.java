package com.bobby.securityjwt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;

/**
 * @className: Role
 * @author: Bobby
 * @date: 10/17/2023
 **/
@Data
@TableName("role")
public class Role implements GrantedAuthority {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Override
    public String getAuthority() {
        return name;
    }
}
