package com.team24.express.controller;

import com.team24.express.common.Result;
import com.team24.express.entity.Employee;
import com.team24.express.entity.User;
import com.team24.express.service.EmployeeService;
import com.team24.express.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @className: ProfileController
 * @author: Bobby
 * @date: 11/8/2023
 * 个人信息控制器
 **/
@Tag(name = "ProfileController", description = "个人信息控制器")
@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Resource
    EmployeeService employeeService;

    @Resource
    UserService userService;

    @Operation(summary = "用户信息", description = "根据ID获取用户信息",
            parameters = {
                    @Parameter(name = "id", description = "账户ID", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回查询结果消息和User实体",
                            content = @Content(schema = @Schema(allOf = {Result.class, User.class}))),
            }
    )
    @GetMapping("/user")
    public Result userProfile(@RequestParam("id") Long id) {
        User user = userService.selectById(id);
        if (Objects.nonNull(user)) {
            Result result = Result.success("获取成功");
            result.setData(user);
            return result;
        }
        return Result.error("获取失败");
    }

    @Operation(summary = "员工信息", description = "根据ID获取员工信息",
            parameters = {
                    @Parameter(name = "id", description = "账户ID", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回查询结果消息和Employee实体",
                            content = @Content(schema = @Schema(allOf = {Result.class, Employee.class}))),
            }
    )
    @GetMapping("/employee")
    public Result employeeProfile(@RequestParam("id") Long id) {
        Employee employee = employeeService.selectById(id);
        if (Objects.nonNull(employee)) {
            Result result = Result.success("获取成功");
            result.setData(employee);
            return result;
        }
        return Result.error("获取失败");
    }

    @Operation(summary = "修改用户信息", description = "修改前端返回的用户信息",
            parameters = {
                    @Parameter(name = "user", description = "用户实体", schema = @Schema(implementation = User.class)),
            },
            responses = {
                    @ApiResponse(description = "返回修改结果消息")
            }
    )
    @PostMapping("/user/edit")
    public Result edit(@RequestBody User user) {
        if (userService.edit(user)) return Result.success("修改成功");
        return Result.error("修改失败");
    }

    @Operation(summary = "修改员工信息", description = "修改前端返回的员工信息",
            parameters = {
                    @Parameter(name = "employee", description = "员工实体", schema = @Schema(implementation = Employee.class)),
            },
            responses = {
                    @ApiResponse(description = "返回修改结果消息")
            }
    )
    @PostMapping("/employee/edit")
    public Result edit2(@RequestBody Employee employee) {
        if (employeeService.edit(employee)) return Result.success("修改成功");
        return Result.error("修改失败");
    }

//    public Result changePwd()
}
