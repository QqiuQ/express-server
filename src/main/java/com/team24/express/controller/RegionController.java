package com.team24.express.controller;

import com.team24.express.common.Result;
import com.team24.express.entity.Delivery;
import com.team24.express.entity.Employee;
import com.team24.express.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @className: RegionCourierController
 * @author: Bobby
 * @date: 11/12/2023
 **/
@Tag(name = "RegionController", description = "获取片区快递员、快件")
@RestController
@RequestMapping("/region")
public class RegionController {
    @Resource
    RegionService regionService;

    @Operation(summary = "片区快递员列表", description = "根据片区(省/市/区)获取快递员列表",
            parameters = {
                    @Parameter(name = "region", description = "片区字符串", schema = @Schema(implementation = String.class)),
            },
            responses = {
                    @ApiResponse(description = "返回快递员(员工)列表", content = @Content(schema = @Schema(implementation = Employee.class)))
            }
    )
    @GetMapping("/couriers")
    public Result getCouriers(@RequestParam("region") String region) {
        List<Employee> employeeList = regionService.getCouriers(region);
        if (Objects.nonNull(employeeList)) {
            Result result = Result.success("查找成功");
            result.setData(employeeList);
            return result;
        }
        return Result.error("查找失败");
    }

    @Operation(summary = "片区快递列表", description = "根据片区(省/市/区)获取片区快递列表。供片区快递员拿去投递",
            parameters = {
                    @Parameter(name = "region", description = "片区字符串", schema = @Schema(implementation = String.class)),
            },
            responses = {
                    @ApiResponse(description = "返回快递列表", content = @Content(schema = @Schema(implementation = Delivery.class)))
            }
    )
    @GetMapping("/deliveries")
    public Result getDeliveries(@RequestParam("region") String region) {
        List<Delivery> deliveryList = regionService.getDeliveries(region);
        if (Objects.nonNull(deliveryList)) {
            Result result = Result.success("查找成功");
            result.setData(deliveryList);
            return result;
        }
        return Result.error("查找失败");
    }

}
