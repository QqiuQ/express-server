package com.team24.express.service;

import java.util.List;

/**
 * @className: UserRoleService
 * @author: Bobby
 * @date: 10/29/2023
 **/
public interface AccountRoleService {
    List<String> getRoleNamesByIdAndType(Long userId, String accountType);
}
