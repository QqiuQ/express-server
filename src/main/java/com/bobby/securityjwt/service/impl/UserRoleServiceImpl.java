package com.bobby.securityjwt.service.impl;

import com.bobby.securityjwt.mapper.UserRoleMapper;
import com.bobby.securityjwt.service.UserRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: UserRoleServiceImpl
 * @author: Bobby
 * @date: 10/29/2023
 **/
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    UserRoleMapper userRoleMapper;

    public List<String> getRoleNamesByIdAndType(Long userId, String accountType) {
        return userRoleMapper.getRoleNamesByIdAndType(userId, accountType);
    }
}
