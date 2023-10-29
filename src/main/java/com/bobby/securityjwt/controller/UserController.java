package com.bobby.securityjwt.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobby.securityjwt.common.AccountConst;
import com.bobby.securityjwt.common.AjaxResult;
import com.bobby.securityjwt.common.Result;
import com.bobby.securityjwt.entity.User;
import com.bobby.securityjwt.entity.UserRole;
import com.bobby.securityjwt.mapper.UserMapper;
import com.bobby.securityjwt.mapper.UserRoleMapper;
import com.bobby.securityjwt.service.UserService;
import com.bobby.securityjwt.util.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @className: UserController
 * @author: Bobby
 * @date: 10/14/2023
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    UserMapper userMapper;

    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    UserRoleMapper userRoleMapper;

    @Resource
    JwtUtils jwtUtils;

//    @GetMapping()
//    public AjaxResult list() {
//        List<User> userList = userService.getUserList();
//        if (Objects.isNull(userList)) return AjaxResult.error("查找错误");
//        return AjaxResult.success(userList);
//    }

    @RequestMapping()
    public AjaxResult list(@Param("pageNow") Integer page, @Param("pageSize") Integer size) {
        IPage<User> userIPage = userMapper.selectPage(new Page<User>(page, size), null);
        return AjaxResult.success(userIPage);
    }

    @GetMapping("/{username}")
    public AjaxResult userInfo(@PathVariable("username") String username) {
        User user = userService.selectByUsername(username);
        if (Objects.isNull(user)) {
            return AjaxResult.error("该用户不存在");
        }
        return AjaxResult.success(user);
    }

    @RequestMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        if (userService.deleteById(id))
            return AjaxResult.success("删除成功");
        else return AjaxResult.error("删除失败");
    }

    @RequestMapping("/add")
    public AjaxResult add(@RequestBody User user) {
        user.setCreateTime(LocalDateTime.now());
        // 密码加密处理
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        int res = userService.insert(user);
        if (res > 0) {
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    @RequestMapping("/edit")
    public AjaxResult edit(@RequestBody User user) {
        int res = userService.update(user);
        if (res > 0) return AjaxResult.success("修改成功");
        return AjaxResult.error("修改失败");
    }

}
