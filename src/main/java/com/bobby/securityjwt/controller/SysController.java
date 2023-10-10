package com.bobby.securityjwt.controller;

import com.bobby.securityjwt.common.AjaxResult;
import com.bobby.securityjwt.entity.User;
import com.bobby.securityjwt.entity.dto.UserDto;
import com.bobby.securityjwt.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @className: SysController
 * @author: Bobby
 * @date: 10/10/2023
 **/
@Tag(name = "SysController")
@RestController("/")
public class SysController {
    @Resource
    UserService userService;
    @Resource
    PasswordEncoder passwordEncoder;


//    @Operation(summary = "登录接口")
//    @RequestMapping("/login")
//    public AjaxResult login(@RequestBody UserDto userDto) {
//        return userService.login(userDto);
//    }
//    public AjaxResult login(String username, String password){}


    @GetMapping("/hello")
    public AjaxResult hello() {
        return AjaxResult.success("hello");
    }

    @PostMapping("pwd/reset")
    public AjaxResult pwdReset(String username, String newPwd) {
        User user = userService.selectByUsername(username);
        if (Objects.isNull(user)) {
            return AjaxResult.error("用户不存在");
        }
        user.setPassword(passwordEncoder.encode(newPwd));
        int updateCode = userService.updateUser(user);
        return AjaxResult.success(updateCode);
    }
}
