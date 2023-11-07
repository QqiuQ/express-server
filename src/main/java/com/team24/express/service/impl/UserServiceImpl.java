package com.team24.express.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team24.express.common.AjaxResult;
import com.team24.express.entity.User;
import com.team24.express.entity.dto.UserDto;
import com.team24.express.mapper.UserMapper;
import com.team24.express.service.UserService;
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

    @Override
    public IPage<User> queryUsersByPage(Page<User> page, User user) {
        QueryWrapper<User> queryWrapper = null;
        if (!Objects.isNull(user)) {
            queryWrapper = new QueryWrapper<>();
            if (Objects.nonNull(user.getUsername()))
                queryWrapper.like("username", user.getUsername());
            if (Objects.nonNull(user.getEmail()))
                queryWrapper.eq("email", user.getEmail());
            if (Objects.nonNull(user.getPhone()))
                queryWrapper.like("phone", user.getPhone());
        }

        return userMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<User> queryList(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (user == null) {
            return userMapper.selectList(wrapper);
        }
        if (user.getUsername() != null) {
            wrapper.like("username", user.getUsername());
        }
        if (user.getPhone() != null) {
            wrapper.eq("phone", user.getPhone());
        }

        return userMapper.selectList(wrapper);
    }

}
