package com.team24.express.controller;

import com.team24.express.common.Result;
import com.team24.express.entity.Delivery;
import com.team24.express.entity.vo.StationDeliveryVo;
import com.team24.express.service.StationDeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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
                                    schema = @Schema(anyOf = {Result.class, StationDeliveryVo.class}))),
            }
    )
    @GetMapping()
    public Result query(@RequestParam("stationId") Long stationId) {
        List<StationDeliveryVo> deliveryList = stationDeliveryService.getDeliverysByStationId(stationId);
        if (Objects.nonNull(deliveryList)) {
            Result result = Result.success("查找成功");
            result.setData(deliveryList);
            return result;
        }
        return Result.error("查找失败");
    }

    @Operation(summary = "运输快递", description = "站点开始运输快递",
            parameters = {
                    @Parameter(name = "id", description = "站点快递关系Id", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回消息"),
            }
    )
    @PostMapping("/shipping")
    public Result shipping(@RequestParam("id") Long id) {
        if (stationDeliveryService.shipping(id)) {
            return Result.success("开始运输");
        }
        return Result.error("运输失败");
    }

    @Operation(summary = "快递出库", description = "工作人员将快递出库",
            parameters = {
                    @Parameter(name = "id", description = "站点快递关系Id", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回消息"),
            }
    )
    @PostMapping("/removestock")
    public Result outOfStock(@RequestParam("id") Long id) {
        if (stationDeliveryService.outOfStock(id)) {
            return Result.success("出库成功");
        }
        return Result.error("出库失败");
    }

    @Operation(summary = "快递入库", description = "工作人员将快递入库",
            parameters = {
                    @Parameter(name = "id", description = "站点快递关系Id", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回消息"),
            }
    )
    @PostMapping("/stocking")
    public Result goStocking(@RequestParam("id") Long id) {
        if (stationDeliveryService.goStocking(id)) {
            return Result.success("已入库");
        }
        return Result.error("入库失败");
    }

}
