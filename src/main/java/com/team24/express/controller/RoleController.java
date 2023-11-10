package com.team24.express.controller;

import com.team24.express.common.Result;
import com.team24.express.entity.Role;
import com.team24.express.entity.vo.EmployeeRoleVo;
import com.team24.express.service.AccountRoleService;
import com.team24.express.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    @Resource
    AccountRoleService accountRoleService;

    @Operation(summary = "获取所有角色")
    @GetMapping()
    public Result getEmployeeRoleList() {
        List<Role> roleList = roleService.selectEmployeeRoles();
        Result result = Result.success("查找成功");
        result.setData(roleList);
        return result;
    }

    //    @PreAuthorize('hasAnyRole()')
    @Operation(summary = "查询特定角色Id的所有员工")
    @GetMapping("/employee")
    public Result getEmployeeByRoleId(@RequestParam("id") Integer id) {
        List<EmployeeRoleVo> employeeRoleVos = accountRoleService.getEmployeeVoListByRoleId(id);
        if (Objects.nonNull(employeeRoleVos)) {
            Result result = Result.success("查找成功");
            result.setData(employeeRoleVos);
            return result;
        }
        return Result.error("查找失败");
    }

    @PostMapping("/change")
    public Result changeRole(@RequestParam("employeeId") Long employeeId,@RequestParam("curRoleId") Integer curRoleId, @RequestParam("newRoleId")Integer newRoleId) {
        if (accountRoleService.changeRole(employeeId, curRoleId, newRoleId)) return Result.success("更改成功");
        return Result.error("更改失败");
    }


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
