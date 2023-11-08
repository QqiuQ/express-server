package com.team24.express.controller;

import com.team24.express.common.Result;
import com.team24.express.entity.Address;
import com.team24.express.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @className: AddressController
 * @author: Bobby
 * @date: 11/8/2023
 **/
@Tag(name = "AddressController", description = "用户地址控制器")
@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    AddressService addressService;

    @Operation(summary = "地址列表", description = "条件查询，返回地址列表",
            parameters = {
                    @Parameter(name = "address", schema = @Schema(implementation = Address.class)),
            },
            responses = {
                    @ApiResponse(description = "返回查询结果消息和用户实体"),
            }
    )
    @PostMapping()
    public Result list(@RequestBody Address address) {
        List<Address> addressList = addressService.queryAddressList(address);
        if (Objects.nonNull(addressList)) {
            Result result = Result.success("查找成功");
            result.setData(addressList);
            return result;
        }
        return Result.error("查找失败");
    }

    @Operation(summary = "编辑地址", description = "根据前端返回的实体对象编辑地址",
            parameters = {
                    @Parameter(name = "address", schema = @Schema(implementation = Address.class)),
            },
            responses = {
                    @ApiResponse(description = "返回编辑结果消息和用户实体"),
            }
    )
    @PostMapping("/edit")
    public Result edit(@RequestBody Address address) {
        if (addressService.edit(address)) return Result.success("修改成功");
        return Result.error("修改失败");
    }

    @Operation(summary = "添加地址", description = "根据前端返回的实体对象添加地址记录",
            parameters = {
                    @Parameter(name = "address", schema = @Schema(implementation = Address.class)),
            },
            responses = {
                    @ApiResponse(description = "返回添加结果消息和用户实体"),
            }
    )
    @PostMapping("/add")
    public Result add(@RequestBody Address address) {
        if (addressService.add(address)) return Result.success("添加成功");
        return Result.error("添加失败");
    }


    @Operation(summary = "删除地址", description = "根据地址ID删除地址",
            parameters = {
                    @Parameter(name = "id", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回删除结果消息"),
            }
    )
    @PostMapping("/delete")
    public Result delete(@RequestParam("id") Long id) {
        if (addressService.delete(id)) return Result.success("删除成功");
        return Result.error("删除失败");
    }

    @Operation(summary = "查看地址", description = "根据地址ID查看单个地址",
            parameters = {
                    @Parameter(name = "id", schema = @Schema(implementation = Long.class)),
            },
            responses = {
                    @ApiResponse(description = "返回删除结果消息和地址"),
            }
    )
    @GetMapping("/info")
    public Result info(@RequestParam("id") Long id) {
        Address address = addressService.selectById(id);
        if (Objects.nonNull(address)) {
            Result result = Result.success("查找成功");
            result.setData(address);
            return result;
        }
        return Result.error("查找失败");
    }
}
