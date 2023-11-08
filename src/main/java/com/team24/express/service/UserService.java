package com.team24.express.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team24.express.common.AjaxResult;
import com.team24.express.entity.User;
import com.team24.express.entity.dto.UserDto;
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


    IPage<User> queryUsersByPage(Page<User> page, User user);

    List<User> queryList(User user);
}
