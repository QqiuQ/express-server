package com.bobby.securityjwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bobby.securityjwt.common.AjaxResult;
import com.bobby.securityjwt.entity.User;
import com.bobby.securityjwt.entity.dto.UserDto;
import com.bobby.securityjwt.mapper.UserMapper;
import com.bobby.securityjwt.service.UserService;
import com.bobby.securityjwt.util.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    private JwtUtils utils;
//    @Resource
//    @Qualifier("authenticationManagerBean")
//    private AuthenticationManager authenticationManager;

    @Override
    public User selectByUsername(String username) {
        // mybatis plus queryWrapper 写法
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public AjaxResult login(UserDto userDto) {
        // 进行登录处理
//
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
//        Authentication authentication = authenticationManager.authenticate(authToken);
//        if (authentication == null) {
//            throw new RuntimeException("Login false");
//        }
//        org.springframework.security.core.userdetails.User authUser = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
//        com.bobby.securityjwt.entity.User user = userMapper.selectByUsername(authUser.getUsername());
//        // 利用用户名和ID生成token
//        String jwt = utils.createJwt(authUser, user.getUsername(), user.getId());
//        if (jwt == null) {
//            return AjaxResult.error(AjaxResult.HttpStatus.FORBIDDEN, "登录验证频繁，请稍后再试");
//        } else {
//            AjaxResult ajax = AjaxResult.success("登录成功");
//            ajax.put("username", user.getUsername());
//            ajax.put("token", jwt);
//            ajax.put("expire", utils.expireTime());
//            return ajax;
//        }
        return AjaxResult.success("success");
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

}
