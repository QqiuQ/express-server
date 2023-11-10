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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @PostMapping("/admins/add")
    public Result addStationAdmin(@RequestParam("stationId") Long stationId, @RequestParam("employeeId") Long employeeId) {
        // 将该员工设置为网点管理员
        Employee stationEmp = employeeService.selectById(employeeId);
        if (Objects.isNull(stationEmp)) return Result.error("该员工不存在");
        // 网点管理员角色
        Role stationAdmin = roleService.getRoleByName(RoleConst.STATION_ADMIN);

        // 更改 account_role 里的角色关联
        accountRoleService.setRole(stationEmp.getId(), stationAdmin.getId());
        // 已经是网点管理员，则与网点添加关联
        // 不能在其他网点充当管理员，只能在其他网点撤销
        StationEmployee stationEmployee = stationEmployeeService.getByEmployeeId(employeeId);
        if (Objects.nonNull(stationEmployee)) {
            return Result.error("该员工已在其他站点充当网点管理员");
        }

        StationEmployee se = new StationEmployee();
        se.setStationId(stationId);
        se.setEmployeeId(employeeId);
        se.setPosition("网点管理员");
        if (stationEmployeeService.add(se)) return Result.success("设置网点管理员成功");

        return Result.error("设置网点管理员失败");
    }


    @Operation(summary = "获取网点所有管理员", description = "根据网点ID获取网点管理员",
            parameters = {
                    @Parameter(name = "stationId", schema = @Schema(implementation = Long.class)),
                    @Parameter(name = "employeeId", schema = @Schema(implementation = Long.class))
            }
            , responses = @ApiResponse(description = "返回消息和列表", content = @Content(schema = @Schema(implementation = EmployeeRoleVo.class)))
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
            , responses = @ApiResponse(description = "返回消息和列表", content = @Content(schema = @Schema(implementation = EmployeeRoleVo.class)))

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
                    @Parameter(name = "employeeId", schema = @Schema(implementation = Long.class)),
            }
    )
    @GetMapping()
    public Result getStationByAdminId(@RequestParam("employeeId") Long employeeId) {
        Station station = stationEmployeeService.getStationByEmployeeId(employeeId);
        if (Objects.nonNull(station)) {
            Result result = Result.success("查找成功");
            result.setData(station);
            return result;
        }
        return Result.error("查找失败");
    }

    @Operation(summary = "检查该管理员是否可用", description = "根据员工ID检查其是否在其他网点担任职务",
            parameters = {
                    @Parameter(name = "employeeId", schema = @Schema(implementation = Long.class)),
            },
            responses = @ApiResponse(description = "可用返回data.status:true; 不可用返回状态信息和任职网点: data.status:false, data.station:...")
    )
    @PostMapping("admins/available/check")
    public Result checkAdminAvailable(@RequestParam("employeeId") Long employeeId) {
        Result result = Result.success();
        Map<String, Object> data = new HashMap<>();
        result.setData(data);
        Station se = stationEmployeeService.getStationByEmployeeId(employeeId);
        if (Objects.nonNull(se)) {
            // 不可用
            data.put("status", false);
            data.put("station", se);
        } else {
            data.put("status", true);
        }
        return result;
    }

    @Operation(summary = "获取可当管理员的员工列表", description = "除了超级管理员之外的所有员工",
            responses = @ApiResponse(description = "可用返回data.status:true; 不可用返回状态信息和任职网点: data.status:false, data.station:...")
    )
    @GetMapping("admins/available")
    public Result getAdminAvailable() {
        List<Employee> availableList = new ArrayList<>();
        List<Employee> empList = employeeService.selectRoleEmployees(RoleConst.EMPLOYEE);
        List<Employee> courierList = employeeService.selectRoleEmployees(RoleConst.DELIVERY_MAN);
        List<Employee> stationAdminList = employeeService.selectRoleEmployees(RoleConst.STATION_ADMIN);
        availableList.addAll(empList);
        availableList.addAll(courierList);
        availableList.addAll(stationAdminList);
        Result result = Result.success("查找成功");
        result.setData(availableList);
        return result;
    }

    @Operation(summary = "获取可当管理员的员工列表", description = "除了超级管理员之外的所有员工",
            parameters = {
                    @Parameter(name = "stationId", schema = @Schema(implementation = Long.class)),
            },
            responses = @ApiResponse(
                    description = "data返回StationEmployee列表",
                    content = @Content(schema = @Schema(implementation = StationEmployee.class)))
    )
    @GetMapping("admins/unavailable")
    public Result getUnAvailableStationEmployees(@RequestParam("stationId") Long stationId) {
        List<StationEmployee> stationEmployees = stationEmployeeService.getByStationId(stationId);
        if (Objects.nonNull(stationEmployees)) {
            Result result = Result.success("查找成功");
            result.setData(stationEmployees);
            return result;
        }
        return Result.error("查找失败");
    }
}
