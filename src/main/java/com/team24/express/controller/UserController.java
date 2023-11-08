package com.team24.express.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team24.express.common.Result;
import com.team24.express.entity.PageParams;
import com.team24.express.entity.User;
import com.team24.express.service.UserService;
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
    PasswordEncoder passwordEncoder;

    @Operation(summary = "用户列表", description = "按User实体属性，条件查询用户列表",
            parameters = {
                    @Parameter(name = "user", description = "前端返回的json实体对象", schema = @Schema(implementation = User.class)),
            },
            responses = {
                    @ApiResponse(description = "返回查询的所有用户",
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


    @Operation(summary = "删除用户", description = "按用户ID删除用户",
            parameters = {
                    @Parameter(name = "id", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回删除结果消息"),
            }
    )
    //    @PreAuthorize("hasAnyRole('SUPER_ADMIN','STATION_ADMIN')")
    @PostMapping("/delete")
    public Result delete(@RequestParam("id") Long id) {
        if (userService.deleteById(id))
            return Result.success("删除成功");
        else return Result.error("删除失败");
    }

    @Operation(summary = "添加用户", description = "添加前端返回的用户",
            parameters = {
                    @Parameter(name = "user", schema = @Schema(implementation = User.class)),
            },
            responses = {
                    @ApiResponse(description = "返回添加结果消息"),
            }
    )
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        user.setCreateTime(LocalDateTime.now());
        // 密码加密处理
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        int res = userService.insert(user);
        if (res > 0) {
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }

    @Operation(summary = "编辑用户", description = "编辑前端返回的用户",
            parameters = {
                    @Parameter(name = "user", schema = @Schema(implementation = User.class)),
            },
            responses = {
                    @ApiResponse(description = "返回编辑结果消息"),
            }
    )
    @PostMapping("/edit")
    public Result edit(@RequestBody User user) {
        user.setUpdateTime(LocalDateTime.now());
        int res = userService.update(user);
        if (res > 0) return Result.success("修改成功");
        return Result.error("修改失败");
    }

    @Operation(summary = "查看用户信息", description = "根据ID查找并返回用户",
            parameters = {
                    @Parameter(name = "id", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回查结果消息和用户实体"),
            }
    )
    @GetMapping("/info")
    public Result info(@RequestParam("id") Long id) {
        User user = userService.selectById(id);
        if (Objects.nonNull(user)) {
            Result result = Result.success("查找成功");
            result.setData(user);
            return result;
        }
        return Result.error("查找失败");
    }

}
