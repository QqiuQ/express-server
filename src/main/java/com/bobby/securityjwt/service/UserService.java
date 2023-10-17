package com.bobby.securityjwt.service;

import com.bobby.securityjwt.entity.User;

import java.util.List;

/**
 * @className: UserService
 * @author: Bobby
 * @date: 10/10/2023
 **/
public interface UserService {
    User selectByUsername(String username);

    List<User> getUserList();

    boolean deleteById(Long id);
}
