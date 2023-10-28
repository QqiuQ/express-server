package com.bobby.securityjwt.controller;

import com.bobby.securityjwt.common.AjaxResult;
import com.bobby.securityjwt.entity.User;
import com.bobby.securityjwt.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @className: UserController
 * @author: Bobby
 * @date: 10/14/2023
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @Resource
    PasswordEncoder passwordEncoder;

    @GetMapping()
    @PreAuthorize("hasAuthority('user:list')")
    public AjaxResult list() {
        List<User> userList = userService.getUserList();
        if (Objects.isNull(userList)) return AjaxResult.error("查找错误");
        return AjaxResult.success(userList);
    }

    @GetMapping("/{username}")
    public AjaxResult info(@PathVariable("username") String username) {
        User user = userService.selectByUsername(username);
        if (Objects.isNull(user)) {
            return AjaxResult.error("该用户不存在");
        }
        return AjaxResult.success(user);
    }

    @RequestMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user:delete')") // 要求当前用户的角色拥有user,delete的权限
    public AjaxResult delete(@PathVariable("id") Long id) {
        if (userService.deleteById(id))
            return AjaxResult.success("删除成功");
        else return AjaxResult.error("删除失败");
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('user:add')")
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
    @PreAuthorize("hasAuthority('user:edit')")
    public AjaxResult edit(@RequestBody User user) {
        int res = userService.update(user);
        if (res > 0) return AjaxResult.success("修改成功");
        return AjaxResult.error("修改失败");
    }

}
