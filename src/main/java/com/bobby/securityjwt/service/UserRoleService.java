package com.bobby.securityjwt.service;

import java.util.List;

/**
 * @className: UserRoleService
 * @author: Bobby
 * @date: 10/29/2023
 **/
public interface UserRoleService {
    List<String> getRoleNamesByIdAndType(Long userId, String accountType);
}
