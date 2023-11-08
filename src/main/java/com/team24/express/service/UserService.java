package com.team24.express.service;

import com.team24.express.entity.User;

import java.util.List;

/**
 * @className: UserService
 * @author: Bobby
 * @date: 10/10/2023
 **/
public interface UserService {
    User selectByUsername(String username);

    boolean deleteById(Long id);

    int insert(User user);

    int update(User user);

    List<User> queryList(User user);

    Boolean updateLastLoginTime(User user);

    User selectById(Long id);
}
