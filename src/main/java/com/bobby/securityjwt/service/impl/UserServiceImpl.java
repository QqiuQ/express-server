package com.bobby.securityjwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bobby.securityjwt.common.AjaxResult;
import com.bobby.securityjwt.entity.User;
import com.bobby.securityjwt.entity.dto.UserDto;
import com.bobby.securityjwt.mapper.RoleMapper;
import com.bobby.securityjwt.mapper.UserMapper;
import com.bobby.securityjwt.service.UserService;
import com.bobby.securityjwt.util.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @className: UserServiceImpl
 * @author: Bobby
 * @date: 10/10/2023
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public User selectByUsername(String username) {
        // mybatis plus queryWrapper 写法
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public List<User> getUserList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        return userMapper.selectList(wrapper);
    }

    @Override
    public boolean deleteById(Long id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public AjaxResult myLogin(UserDto userDto, HttpServletResponse response) {
        User user = userMapper.selectByUsername(userDto.getUsername());

        if (Objects.isNull(user)) {
            return AjaxResult.error("用户不存在");
        }
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            return AjaxResult.error("密码错误");
        }
        return AjaxResult.success("登录成功");
    }

}
