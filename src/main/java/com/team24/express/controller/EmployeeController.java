package com.team24.express.controller;

import com.team24.express.common.Result;
import com.team24.express.entity.Employee;
import com.team24.express.entity.vo.EmployeeRoleFullVo;
import com.team24.express.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @className: EmployeeController
 * @author: Bobby
 * @date: 10/14/2023
 **/
@Tag(name = "EmployeeController", description = "员工控制器")
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    EmployeeService employeeService;

    @Operation(summary = "获取员工信息", description = "根据员工ID获取员工信息",
            parameters = {
                    @Parameter(name = "id", schema = @Schema(implementation = Long.class))
            },
            responses = @ApiResponse(description = "Employee封装在Result的data里",
                    content = @Content(schema = @Schema(allOf = {Result.class, Employee.class}))))
    @GetMapping()
    public Result info(@RequestParam("id") Long id) {
        Employee employee = employeeService.selectById(id);
        if (Objects.nonNull(employee)) {
            Result result = Result.success("查找成功");
            result.setData(employee);
            return result;
        }
        return Result.error("查找失败");
    }

//    @Operation(summary = "条件查询员工", description = "根据员工实体部分属性，条件查询，返回员工列表。",
//            parameters = {
//                    @Parameter(name = "employee", schema = @Schema(implementation = Employee.class))
//            },
//            responses = @ApiResponse(description = "列表封装在Result的data里"))
//    @PostMapping()
//    public Result query(@RequestBody Employee employee) {
//        List<Employee> employeeList = employeeService.selectEmployeeList(employee);
//        if (Objects.nonNull(employeeList)) {
//            Result result = Result.success("查找成功");
//            result.setData(employeeList);
//            return result;
//        }
//        return Result.error("查找失败");
//    }

    @Operation(summary = "条件查询员工", description = "根据员工实体部分属性，条件查询，返回员工列表。",
            parameters = {
                    @Parameter(name = "employee", schema = @Schema(implementation = EmployeeRoleFullVo.class))
            },
            responses = @ApiResponse(description = "列表封装在Result的data里"))
    @PostMapping()
    public Result query(@RequestBody EmployeeRoleFullVo employee) {
        List<EmployeeRoleFullVo> employeeList = employeeService.selectEmployeeList(employee);
        if (Objects.nonNull(employeeList)) {
            Result result = Result.success("查找成功");
            result.setData(employeeList);
            return result;
        }
        return Result.error("查找失败");
    }

    @Operation(summary = "添加员工", description = "根据前端返回的json实体，添加员工",
            parameters = {
                    @Parameter(name = "employee", schema = @Schema(implementation = EmployeeRoleFullVo.class))
            },
            responses = @ApiResponse(description = "返回添加结果的消息"))
    @PostMapping("/add")
    public Result add(@RequestBody EmployeeRoleFullVo employee) {
        if (employeeService.add(employee)) {
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }

    @Operation(summary = "编辑员工", description = "根据前端修改的json实体，编辑员工",
            parameters = {
                    @Parameter(name = "employee", schema = @Schema(implementation = Employee.class))
            },
            responses = @ApiResponse(description = "返回编辑结果的消息"))
    @PostMapping("edit")
    public Result edit(@RequestBody Employee employee) {
        if (employeeService.edit(employee)) {
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }

    @Operation(summary = "删除员工", description = "根据前端传回的ID值删除员工",
            parameters = {
                    @Parameter(name = "id", schema = @Schema(implementation = Long.class))
            },
            responses = @ApiResponse(description = "返回删除结果的消息"))
    @PostMapping("/delete")
    public Result delete(@RequestParam("id") Long id) {
        if (employeeService.delete(id)) return Result.success("删除成功");
        return Result.error("删除失败");
    }


}
