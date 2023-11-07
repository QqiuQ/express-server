package com.team24.express.config.security.userdetails;

import com.team24.express.entity.Account;
import com.team24.express.entity.Employee;
import com.team24.express.entity.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @className: AccountDetails
 * @author: Bobby
 * @date: 10/29/2023
 **/
@Data
public class AccountDetails implements UserDetails {
    private String username;
    private String password;
    private Integer accountStatus;
    private String accountType;
    private List<Role> roleList;

    public AccountDetails(Account account, List<Role> permissionAuthorities) {
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.accountStatus = account.getAccountStatus();
        this.roleList = permissionAuthorities;
    }

    public AccountDetails(Account account, List<Role> roleList, String typeUser) {
        this(account, roleList);
        this.accountType = typeUser;
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountStatus == Employee.ACCOUNT_STATUS_NORMAL;
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
