package com.bobby.securityjwt.config.security.userdetails;

import com.bobby.securityjwt.entity.Employee;
import com.bobby.securityjwt.entity.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @className: EmployeeUserDetails
 * @author: Bobby
 * @date: 10/17/2023
 **/
@Data
public class EmployeeDetails implements UserDetails {

    private String username;
    private String password;
    private Integer accountStatus;
    private List<Role> roleList;

    public EmployeeDetails(Employee employee, List<Role> roleList) {
        this.username = employee.getUsername();
        this.password = employee.getPassword();
        this.accountStatus = employee.getAccountStatus();
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
