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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "UserController", description = "用户相关接口")
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

    @Operation(summary = "用户列表(分页)", description = "以分页形式返回所有用户",
            parameters = {
                    @Parameter(name = "pageNow", description = "当前页"),
                    @Parameter(name = "pageSize", description = "页大小")
            },
            responses = {
                    @ApiResponse(description = "返回当前页的所有用户",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(anyOf = {Result.class, User.class}))),
            }
    )
    @GetMapping()
    public AjaxResult list(Integer pageNow, Integer pageSize) {
        IPage<User> userIPage = userMapper.selectPage(new Page<User>(pageNow, pageSize), null);
        return AjaxResult.success(userIPage);
    }

    @Operation(summary = "用户信息", description = "根据用户名查看用户信息",
            parameters = {
                    @Parameter(name = "username", description = "用户名"),
            })
    @ApiResponse(description = "若用户存在，则返回用户信息",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)))
    @GetMapping("/{username}")
    public AjaxResult userinfo(@PathVariable("username") String username) {
        User user = userService.selectByUsername(username);
        if (Objects.isNull(user)) {
            return AjaxResult.error("该用户不存在");
        }
        return AjaxResult.success(user);
    }

    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        if (userService.deleteById(id))
            return AjaxResult.success("删除成功");
        else return AjaxResult.error("删除失败");
    }

    @PostMapping("/add")
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

    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody User user) {
        int res = userService.update(user);
        if (res > 0) return AjaxResult.success("修改成功");
        return AjaxResult.error("修改失败");
    }

}
