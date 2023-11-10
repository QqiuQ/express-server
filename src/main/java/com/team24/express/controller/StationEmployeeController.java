package com.team24.express.controller;

import com.team24.express.common.AccountConst;
import com.team24.express.common.Result;
import com.team24.express.common.RoleConst;
import com.team24.express.entity.*;
import com.team24.express.entity.vo.EmployeeRoleVo;
import com.team24.express.service.AccountRoleService;
import com.team24.express.service.EmployeeService;
import com.team24.express.service.RoleService;
import com.team24.express.service.StationEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @className: StationEmployeeController
 * @author: Bobby
 * @date: 11/10/2023
 **/
@RestController
@RequestMapping("/station")
public class StationEmployeeController {

    @Resource
    EmployeeService employeeService;
    @Resource
    AccountRoleService accountRoleService;
    @Resource
    RoleService roleService;
    @Resource
    StationEmployeeService stationEmployeeService;


    @Operation(summary = "设置网点管理员", description = "根据网点ID和员工ID设置网点管理员",
            parameters = {
                    @Parameter(name = "stationId", schema = @Schema(implementation = Long.class)),
                    @Parameter(name = "employeeId", schema = @Schema(implementation = Long.class))
            }
    )
    @PostMapping("/set-admin")
    public Result setStationAdmin(@RequestParam("stationId") Long stationId, @RequestParam("employeeId") Long employeeId) {
        // 将该员工设置为网点管理员
        Employee stationEmp = employeeService.selectById(employeeId);
        if (Objects.isNull(stationEmp)) return Result.error("该员工不存在");
        // 网点管理员角色
        Role stationAdmin = roleService.getRoleByName(RoleConst.STATION_ADMIN);

        // 如果account_role不存在则插入新的关系
        AccountRole accountRole = accountRoleService.selectByAccountIdAndRoleId(stationEmp.getId(), stationAdmin.getId());
        if (Objects.isNull(accountRole)) {
            accountRoleService.add(new AccountRole(stationEmp.getId(), stationAdmin.getId(), AccountConst.TYPE_EMPLOYEE));
            accountRole = accountRoleService.selectByAccountIdAndRoleId(stationEmp.getId(), stationAdmin.getId());
        }
        // 已经是网点管理员，则与网点添加关联
        StationEmployee se = new StationEmployee();
        se.setStationId(stationId);
        se.setEmployeeId(employeeId);
        se.setPosition("网点管理员");
        if (stationEmployeeService.add(se)) return Result.success("设置管理员成功");

        return Result.error("设置管理员失败");
    }


    @Operation(summary = "获取网点所有管理员", description = "根据网点ID获取网点管理员",
            parameters = {
                    @Parameter(name = "stationId", schema = @Schema(implementation = Long.class)),
                    @Parameter(name = "employeeId", schema = @Schema(implementation = Long.class))
            }
    )
    @GetMapping("/admins")
    public Result getAdmins(@RequestParam("stationId") Long stationId) {
        List<EmployeeRoleVo> admins = stationEmployeeService.getAdminsById(stationId);
        if (Objects.nonNull(admins)) {
            Result result = Result.success("查找成功");
            result.setData(admins);
            return result;
        }
        return Result.error("查找失败");
    }

    @Operation(summary = "获取网点所有员工", description = "根据网点ID获取所有员工,包括管理员快递员。前端可通过过滤的方式筛选出快递员",
            parameters = {
                    @Parameter(name = "stationId", schema = @Schema(implementation = Long.class)),
                    @Parameter(name = "employeeId", schema = @Schema(implementation = Long.class))
            }
    )
    @GetMapping("/employees")
    public Result getEmployees(@RequestParam("stationId") Long stationId) {
        List<EmployeeRoleVo> admins = stationEmployeeService.getEmployeesById(stationId);
        if (Objects.nonNull(admins)) {
            Result result = Result.success("查找成功");
            result.setData(admins);
            return result;
        }
        return Result.error("查找失败");
    }

    @Operation(summary = "获取管理员关联站点信息", description = "根据网点管理员Id获取其管理的站点信息(一个管理员只能管理一个站点)",
            parameters = {
                    @Parameter(name = "adminId", schema = @Schema(implementation = Long.class)),
            }
    )
    @GetMapping()
    public Result getStationByAdminId(@RequestParam("employeeId") Long employeeId) {
        Station station = stationEmployeeService.getStationByAdminId(employeeId);
        if (Objects.nonNull(station)) {
            Result result = Result.success("查找成功");
            result.setData(station);
            return result;
        }
        return Result.error("查找失败");
    }




}
