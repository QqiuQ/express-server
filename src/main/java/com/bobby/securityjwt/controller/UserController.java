package com.bobby.securityjwt.controller;

import com.bobby.securityjwt.common.AjaxResult;
import com.bobby.securityjwt.entity.User;
import com.bobby.securityjwt.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/test")
    public AjaxResult test() {
        return AjaxResult.success("test success");
    }

    @GetMapping()
    @PostAuthorize("hasAnyAuthority('ROLE_USER')")
    public AjaxResult list() {
        List<User> userList = userService.getUserList();
        if (Objects.isNull(userList)) return AjaxResult.error("查找错误");
        return AjaxResult.success(userList);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasPermission('user','read')")   // 要求当前用户的角色拥有user,delete的权限
    public AjaxResult info(@PathVariable("username") String username) {
        User user = userService.selectByUsername(username);
        if (Objects.isNull(user)) {
            return AjaxResult.error("该用户不存在");
        }
        return AjaxResult.success(user);
    }

    @RequestMapping("/delete/{id}")
    @PreAuthorize("hasPermission('user','delete')") // 要求当前用户的角色拥有user,delete的权限
    public AjaxResult delete(@PathVariable("id") Long id) {
        if (userService.deleteById(id))
            return AjaxResult.success("删除成功");
        else return AjaxResult.error("删除失败");
    }

}
