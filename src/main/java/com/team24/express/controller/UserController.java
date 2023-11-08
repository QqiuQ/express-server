package com.team24.express.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team24.express.common.AjaxResult;
import com.team24.express.common.Result;
import com.team24.express.entity.PageParams;
import com.team24.express.entity.User;
import com.team24.express.entity.dto.QueryDto;
import com.team24.express.mapper.UserMapper;
import com.team24.express.mapper.AccountRoleMapper;
import com.team24.express.service.UserService;
import com.team24.express.util.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
    AccountRoleMapper accountRoleMapper;

    @Resource
    JwtUtils jwtUtils;

    @Operation(summary = "用户列表(分页)", description = "以分页形式返回所有用户",
            parameters = {
                    @Parameter(name = "query", description = "查询对象", schema = @Schema(implementation = QueryDto.class)),
            },
            responses = {
                    @ApiResponse(description = "返回当前页的所有用户",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(anyOf = {Result.class, User.class}))),
            }
    )
    @PostMapping()
    public Result list(@RequestBody User user) {
        List<User> userList = userService.queryList(user);
        Result result = Result.success("查询成功");
        result.setData(userList);
        return result;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Result queryList(@RequestBody PageParams pageParams, @RequestBody User user) {
        if (Objects.isNull(pageParams)) {
            return Result.error("query为空");
        }
        Page<User> page = new Page<>(pageParams.getPage(), pageParams.getLimit());
        IPage<User> userIPage = userService.queryUsersByPage(page, new User());
        Result result = Result.success("查询成功");
        result.setData(userIPage);
        return result;
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
