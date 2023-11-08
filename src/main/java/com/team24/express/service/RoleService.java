package com.team24.express.service;

import com.team24.express.entity.Role;

/**
 * @className: RoleService
 * @author: Bobby
 * @date: 11/8/2023
 **/
public interface RoleService {
    boolean insert(Role role);

    boolean edit(Role role);

    boolean delete(Integer id);
}
