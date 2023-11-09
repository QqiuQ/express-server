package com.team24.express.controller;

import com.team24.express.common.Result;
import com.team24.express.entity.Address;
import com.team24.express.entity.Delivery;
import com.team24.express.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Objects;

/**
 * @className: DeliveryController
 * @author: Bobby
 * @date: 11/8/2023
 **/
@Tag(name = "DeliveryController", description = "快件、订单相关控制器")
@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Resource
    DeliveryService deliveryService;

    @Operation(summary = "添加物流", description = "根据前端返回实体添加",
            parameters = {
                    @Parameter(name = "delivery", schema = @Schema(implementation = Delivery.class)),
            },
            responses = {
                    @ApiResponse(description = "返回添加结果消息"),
            }
    )
    @PostMapping("/add")
    public Result add(Delivery delivery) {
        if (deliveryService.add(delivery)) return Result.success("添加成功");
        return Result.error("添加失败");
    }

//    @Operation(summary = "预估价格", description = "预估两地的物流价格",
//            parameters = {
//                    @Parameter(name = "src", schema = @Schema(implementation = String.class)),
//                    @Parameter(name = "tar", schema = @Schema(implementation = String.class)),
//
//            },
//            responses = {
//                    @ApiResponse(description = "返回查询结果消息和订单列表"),
//            }
//    )
//    @PostMapping("/price")
//    public Result price(@RequestParam("srcCode") String srcCode, @RequestParam("dstCode") String dstCode) {
//        return null;
//    }

    @Operation(summary = "收到订单", description = "根据手机号查询用户物流订单",
            parameters = {
                    @Parameter(name = "phone", schema = @Schema(implementation = String.class)),
            },
            responses = {
                    @ApiResponse(description = "返回查询结果消息和订单列表"),
            }
    )
    @GetMapping("/receive")
    public Result receive(@RequestParam("phone") String phone) {
        List<Delivery> deliveryList = deliveryService.selectByReceivePhone(phone);
        if (Objects.nonNull(deliveryList)) {
            Result result = Result.success("查找成功");
            result.setData(deliveryList);
            return result;
        }
        return Result.error("查找失败");
    }

    @Operation(summary = "删除订单", description = "根据订单ID删除订单",
            parameters = {
                    @Parameter(name = "id", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回删除结果消息"),
            }
    )
    @PostMapping("/delete")
    public Result delete(@RequestParam("id") Long id) {
        if (deliveryService.deleteById(id)) return Result.success("删除成功");
        return Result.error("删除失败");
    }

    @Operation(summary = "取消订单", description = "用户取消订单",
            parameters = {
                    @Parameter(name = "id", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回取消结果消息"),
            }
    )
    @PostMapping("/cancel")
    public Result cancel(@RequestParam("id") Long id) {
        if (deliveryService.updateStatus(id, Delivery.STATUS_CANCEL)) return Result.success("取消成功");
        return Result.error("取消失败");
    }

}
