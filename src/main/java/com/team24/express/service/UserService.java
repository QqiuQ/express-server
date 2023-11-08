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

    @Deprecated
    boolean deleteById(Long id);

    @Deprecated
    int insert(User user);

    @Deprecated
    int update(User user);

    Boolean add(User user);

    Boolean edit(User user);

    Boolean delete(Long id);

    List<User> selectUserList(User user);

    Boolean updateLastLoginTime(User user);

    User selectById(Long id);
}
