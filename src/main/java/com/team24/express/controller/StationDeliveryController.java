package com.team24.express.controller;

import com.team24.express.common.Result;
import com.team24.express.entity.Delivery;
import com.team24.express.entity.User;
import com.team24.express.service.DeliveryService;
import com.team24.express.service.StationDeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @className: StationDeliveryController
 * @author: Bobby
 * @date: 11/11/2023
 **/
@Tag(name = "StationDeliveryController")
@RestController
@RequestMapping("/station/delivery")
public class StationDeliveryController {

    @Resource
    StationDeliveryService stationDeliveryService;

    @Operation(summary = "查询本网点的订单", description = "根据网点ID返回该网点所有订单",
            parameters = {
                    @Parameter(name = "stationId", description = "网点ID", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回查询消息和订单列表",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(anyOf = {Result.class, Delivery.class}))),
            }
    )
    @PostMapping()
    public Result query(@RequestParam("stationId") Long stationId) {
        List<Delivery> deliveryList = stationDeliveryService.getDeliverysByStationId(stationId);
        if (Objects.nonNull(deliveryList)) {
            Result result = Result.success("查找成功");
            result.setData(deliveryList);
            return result;
        }
        return Result.error("查找失败");
    }

}
