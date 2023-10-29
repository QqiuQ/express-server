package com.bobby.securityjwt.config.security.userdetails;

import com.bobby.securityjwt.common.AccountConst;
import com.bobby.securityjwt.service.SecurityService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @className: AccountDetailService
 * @author: Bobby
 * @date: 10/29/2023
 **/
@Component
public class AccountDetailService implements UserDetailsService {
    @Resource
    SecurityService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 前端传过来的username为 username+"#"+accountType,so
        String[] strings = StringUtils.split(username, '#');
        String uname = strings[0];
        String aType = null;
        if (strings.length > 1) {
            aType = strings[1];
        }
        if (aType != null && aType.equals(AccountConst.TYPE_EMPLOYEE)) {
            return service.getEmployeeDetails(uname);
        } else {
            return service.getUserDetails(uname);
        }
    }
}
