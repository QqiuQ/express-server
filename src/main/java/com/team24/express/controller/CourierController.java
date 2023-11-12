package com.team24.express.controller;

import com.team24.express.common.Result;
import com.team24.express.entity.CourierDelivery;
import com.team24.express.entity.Delivery;
import com.team24.express.entity.StationDelivery;
import com.team24.express.service.CourierService;
import com.team24.express.service.StaitonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/courier")
public class CourierController {
    @Resource
    CourierService courierService;

    @Resource
    StaitonService stationService;

    @Operation(summary = "快递员接派送快递任务", description = "新增快递员与快递关系",
            parameters = {
                    @Parameter(name = "delivery", description = "关系对象", schema = @Schema(implementation = CourierDelivery.class)),
            },
            responses = {
                    @ApiResponse(description = "返回消息")}
    )
    @PostMapping("/delivery/accept")
    public Result accept(@RequestBody CourierDelivery delivery) {
        if (courierService.acceptDelivery(delivery)) return Result.success("接受派送任务");
        return Result.error("接受失败");
    }

    @Operation(summary = "派送到站点", description = "快递员将快递派送到站点",
            parameters = {
                    @Parameter(name = "taskId", description = "快递员与快递关系Id", schema = @Schema(implementation = Long.class)),
                    @Parameter(name = "deliveryId", description = "快递Id", schema = @Schema(implementation = Long.class)),
                    @Parameter(name = "stationId", description = "站点Id", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回消息")}
    )
    @PostMapping("/delivery/finish")
    public Result accept(@RequestParam("taskId") Long taskId,
                         @RequestParam("deliveryId") Long deliveryId,
                         @RequestParam("stationId") Long stationId) {
        // 关系设为已完成
        if (!courierService.finishTask(taskId)) return Result.error("系统出错，任务完成失败");
        // 添加到站点快递表
        StationDelivery stationDelivery = new StationDelivery();
        stationDelivery.setStationId(stationId);
        stationDelivery.setDeliveryId(deliveryId);
        stationService.packageInRep(stationDelivery);
        return Result.success("派送任务完成");
    }

    @Operation(summary = "获取快递员待揽收快递列表", description = "快递员揽收快递",
            parameters = {
                    @Parameter(name = "courierId", description = "快递员Id(员工Id)", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回消息和列表",
                            content = @Content(schema = @Schema(implementation = Delivery.class)))}
    )
    @GetMapping("/delivery/collect")
    public Result waitForCollectList(@RequestParam("courierId") Long courierId) {
        List<Delivery> deliveryList = courierService.getCollectingBy(courierId, CourierDelivery.STATUS_COLLECTING);
        if (Objects.nonNull(deliveryList)) {
            Result result = Result.success("查询成功");
            result.setData(deliveryList);
            return result;
        }
        return Result.error("查询失败");
    }

    @Operation(summary = "获取快递员待妥投快递列表", description = "快递员妥投快递",
            parameters = {
                    @Parameter(name = "courierId", description = "快递员Id(员工Id)", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回消息和列表",
                            content = @Content(schema = @Schema(implementation = Delivery.class)))}
    )
    @GetMapping("/delivery/dispatch")
    public Result waitForDispatchList(@RequestParam("courierId") Long courierId) {
        List<Delivery> deliveryList = courierService.getCollectingBy(courierId, CourierDelivery.STATUS_DISPATCHING);
        if (Objects.nonNull(deliveryList)) {
            Result result = Result.success("查询成功");
            result.setData(deliveryList);
            return result;
        }
        return Result.error("查询失败");
    }

    @Operation(summary = "确认妥投", description = "快递员妥投快递确认,将快递送达站点，在快递单上留下自己的信息",
            parameters = {
                    @Parameter(name = "deliveryId", description = "快递Id", schema = @Schema(implementation = Long.class)),
                    @Parameter(name = "courierId", description = "快递员Id", schema = @Schema(implementation = Long.class)),
                    @Parameter(name = "stationId", description = "站点Id", schema = @Schema(implementation = Long.class))
            },
            responses = {
                    @ApiResponse(description = "返回消息和列表")}
    )
    @PostMapping("/delivery/dispatch/confirm")
    public Result confirmDispatched(@RequestParam("deliveryId") Long deliveryId, @RequestParam("courierId") Long courierId, @RequestParam("stationId") Long stationId) {
        if (courierService.confirmDispatched(deliveryId, courierId, stationId)) {
            Result result = Result.success("妥投成功");
            return result;
        }
        return Result.error("妥投失败");
    }

    @Operation(summary = "确认揽收", description = "快递员揽收快递, 并投递到网点,由网点进行处理",
            parameters = {
                    @Parameter(name = "deliveryId", description = "关系表id", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回消息和列表")}
    )
    @PostMapping("/delivery/collect/confirm")
    public Result confirmCollected(@RequestParam("deliveryId") Long deliveryId, @RequestParam("stationId") Long stationId) {
        if (courierService.confirmCollected(deliveryId, stationId)) {
            Result result = Result.success("揽收成功");
            return result;
        }
        return Result.error("揽收失败");
    }

}
