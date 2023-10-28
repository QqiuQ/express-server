package com.bobby.securityjwt.service;

import com.bobby.securityjwt.common.AjaxResult;
import com.bobby.securityjwt.entity.User;
import com.bobby.securityjwt.entity.dto.UserDto;
import jakarta.servlet.http.HttpServletResponse;

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

    int insert(User user);

    int update(User user);

    AjaxResult myLogin(UserDto userDto, HttpServletResponse response);
}
