package com.team24.express.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team24.express.entity.User;
import com.team24.express.mapper.UserMapper;
import com.team24.express.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public boolean deleteById(Long id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("id", user.getId());
//        wrapper.eq("username", user.getUsername());
        return userMapper.update(user, wrapper);
    }

    @Override
    public Boolean add(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userMapper.insert(user) > 0) return true;
        return false;
    }

    @Override
    public Boolean edit(User user) {
        user.setUpdateTime(LocalDateTime.now());
        if (userMapper.updateById(user) > 0) return true;
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        if (userMapper.deleteById(id) > 0) return true;
        return false;
    }

    @Override
    public List<User> selectUserList(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (user == null) {
            return userMapper.selectList(wrapper);
        }
        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            wrapper.like("username", user.getUsername());
        }
        if (user.getPhone() != null && !user.getPhone().isEmpty()) {
            wrapper.like("phone", user.getPhone());
        }
        if (user.getNickname() != null && !user.getNickname().isEmpty()) {
            wrapper.like("nickname", user.getNickname());
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            wrapper.like("email", user.getEmail());
        }
        if (user.getSex() != null) {
            wrapper.eq("sex", user.getSex());
        }
        if (user.getBirthday() != null) {
            wrapper.eq("birth", user.getBirthday());
        }
        if (user.getAccountStatus() != null) {
            wrapper.eq("account_status", user.getAccountStatus());
        }


        return userMapper.selectList(wrapper);
    }

    @Override
    public Boolean updateLastLoginTime(User user) {
        user.setLastLoginTime(LocalDateTime.now());
        if (userMapper.updateById(user) > 0) return true;
        return false;
    }

    @Override
    public User selectById(Long id) {

        return userMapper.selectById(id);
    }

}
