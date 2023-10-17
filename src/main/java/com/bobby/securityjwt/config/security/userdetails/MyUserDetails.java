package com.bobby.securityjwt.config.security.userdetails;

import com.bobby.securityjwt.entity.Role;
import com.bobby.securityjwt.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @className: MyUserDetails
 * @author: Bobby
 * @date: 10/17/2023
 **/
@Data
public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private Integer accountStatus;
    private List<Role> roleList;

    public MyUserDetails(User user, List<Role> roleList) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.accountStatus = user.getAccountStatus();
        this.roleList = roleList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return accountStatus == User.ACCOUNT_STATUS_NORMAL;
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
