package com.team24.express.config.security.userdetails;

import com.team24.express.common.AccountConst;
import com.team24.express.service.SecurityService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AccountDetailService implements UserDetailsService {
    @Resource
    SecurityService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 前端传过来的username为 username+"#"+accountType,so
        int idx = StringUtils.lastIndexOf(username, '#');
        try {
            String uname = username.substring(0, idx);
            String aType = username.substring(idx+1);

            if (aType != null && aType.equals(AccountConst.TYPE_EMPLOYEE)) {
                return service.getEmployeeDetails(uname);
            } else {
                return service.getUserDetails(uname);
            }
        } catch (Exception e) {
            log.error("用户名解析错误");
            e.printStackTrace();
            throw e;
        }
    }
}
