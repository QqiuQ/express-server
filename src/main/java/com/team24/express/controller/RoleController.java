package com.team24.express.controller;

import com.team24.express.common.Result;
import com.team24.express.entity.Role;
import com.team24.express.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @className: RoleController
 * @author: Bobby
 * @date: 11/8/2023
 **/
@Tag(name = "RoleController", description = "角色相关接口")   // ApiDoc相关
@RestController
@RequestMapping("/role")    // 请求地址
public class RoleController {
    @Resource
    RoleService roleService;    // 依赖注入


    /**
     * @param role 角色实体
     * @return
     */
    @Operation(summary = "角色添加", description = "添加前端返回的json对象实体",
            parameters = {
                    @Parameter(name = "role", description = "角色实体", schema = @Schema(implementation = Role.class))
            },
            responses = {
                    @ApiResponse(description = "返回添加结果",
                            content = @Content(schema = @Schema(implementation = Result.class)))
            }
    )
    @PostMapping("/add")
    public Result add(@RequestBody Role role) {
        role.setCreateTime(LocalDateTime.now());
        if (roleService.insert(role)) return Result.success("添加成功");
        return Result.error("添加失败");
    }

    @Operation(summary = "角色编辑", description = "前端修改角色实体后返回给后端",
            parameters = {
                    @Parameter(name = "role", description = "角色实体", schema = @Schema(implementation = Role.class))
            },
            responses = {
                    @ApiResponse(description = "返回编辑结果",
                            content = @Content(schema = @Schema(implementation = Result.class)))
            }
    )
    @PostMapping("/edit")
    public Result edit(@RequestBody Role role) {
        role.setUpdateTime(LocalDateTime.now());
        if (roleService.edit(role)) return Result.success("修改成功");
        return Result.error("修改失败");
    }

    @Operation(summary = "角色删除", description = "根据前端传回的id删除角色",
            parameters = {
                    @Parameter(name = "id", description = "角色id", schema = @Schema(implementation = Integer.class))
            },
            responses = {
                    @ApiResponse(description = "返回删除结果",
                            content = @Content(schema = @Schema(implementation = Result.class)))
            }
    )
    @PostMapping("/delete")
    public Result delete(@RequestParam("id") Integer id) {
        return null;
    }
}
