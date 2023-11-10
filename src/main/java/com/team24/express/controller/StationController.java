package com.team24.express.controller;


import com.team24.express.common.Result;
import com.team24.express.entity.*;
import com.team24.express.service.EmployeeService;
import com.team24.express.service.StaitonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Tag(name = "StationController", description = "站点相关接口")
@RestController
@Slf4j
@RequestMapping("/station")
public class StationController {

    @Autowired
    StaitonService stationService;

    @Autowired
    EmployeeService employeeService;


    /**
     * 根据传入的参数查询符合要求的所有包裹
     *
     * @param delivery
     * @return
     */
    @Operation(summary = "条件查询快递", description = "根据返回的实体进行条件查询",
            parameters = {
                    @Parameter(name = "delivery", schema = @Schema(implementation = Delivery.class)),
            },
            responses = @ApiResponse(description = "返回消息和快递列表",
                    content = @Content(schema = @Schema(implementation = Delivery.class)))
    )
    @PostMapping("/delivery")
    public Result selectPackages(@RequestBody Delivery delivery) {
        List<Delivery> orderList = stationService.selectPackages(delivery);
        if (orderList.isEmpty())
            return Result.error("未查询到数据！");
        else {
            Result r = Result.success("查询成功！");
            r.setData(orderList);
            return r;
        }
    }


    /**
     * 快递入库（订单与网点关系表中新增）
     *
     * @param stationOrder
     * @return
     */
    @Operation(summary = "快递入库", description = "在站点快递表中新增关系",
            parameters = {
                    @Parameter(name = "stationOrder", schema = @Schema(implementation = StationOrder.class)),
            },
            responses = @ApiResponse(description = "返回消息"
            )
    )
    @PostMapping("/delivery/add")
    public Result packageInRep(@RequestBody StationOrder stationOrder) {
        stationService.packageInRep(stationOrder);
        return Result.success("快递入库成功！");
    }

    /**
     * 快递出库（订单与网点关系表中修改订单的状态为已出库）
     *
     * @param stationOrder
     * @return
     */
    @Operation(summary = "快递出库", description = "从站点快递表删除关系",
            parameters = {
                    @Parameter(name = "stationOrder", schema = @Schema(implementation = StationOrder.class)),
            },
            responses = @ApiResponse(description = "返回消息"
            )
    )
    @PostMapping("/delivery/remove")
    public Result packageOutRep(@RequestBody StationOrder stationOrder) {
        stationService.packageOutRep(stationOrder);
        return Result.success("快递出库成功！");
    }

    /**
     * 录入快递员（先在employee查询是否存在该员工，若存在将其信息加入网点员工表中）
     *
     * @param employeeId
     * @param stationId
     * @return
     */
    @Operation(summary = "添加网点快递员", description = "网点关系表内添加网点与员工关系",
            parameters = {
                    @Parameter(name = "employeeId", schema = @Schema(implementation = Long.class)),
                    @Parameter(name = "stationId", schema = @Schema(implementation = Long.class)),
            },
            responses = @ApiResponse(description = "返回消息"
            )
    )
    @PostMapping("/courier/add")
    public Result addNewCourier(@RequestParam Long employeeId, @RequestParam Long stationId) {
        Employee courier = employeeService.selectById(employeeId);
        if (Objects.isNull(courier))
            return Result.error("不存在该员工");
        else {
            StationEmployee e = new StationEmployee();
            e.setStationId(stationId);
            e.setCreateTime(LocalDateTime.now());
            e.setUpdateTime(LocalDateTime.now());
            e.setEmployeeId(courier.getId());
            e.setPosition("网点快递员");
            stationService.addNewCourier(e);
            return Result.success("录入成功");
        }
    }

    @Operation(summary = "添加员工", description = "网点关系表内添加网点与员工关系",
            parameters = {
                    @Parameter(name = "employeeId", schema = @Schema(implementation = Long.class)),
                    @Parameter(name = "stationId", schema = @Schema(implementation = Long.class)),
            },
            responses = @ApiResponse(description = "返回消息"
            )
    )
    @PostMapping("/employee/add")
    public Result addEmployee(@RequestParam Long employeeId, @RequestParam Long stationId) {
        Employee courier = employeeService.selectById(employeeId);
        if (Objects.isNull(courier))
            return Result.error("不存在该员工");
        else {
            StationEmployee e = new StationEmployee();
            e.setStationId(stationId);
            e.setCreateTime(LocalDateTime.now());
            e.setUpdateTime(LocalDateTime.now());
            e.setEmployeeId(courier.getId());
            e.setPosition("网点员工");
            stationService.addNewCourier(e);

            return Result.success("录入成功");
        }
    }

    /**
     * 根据员工实体查询本网点的快递员（通过匹配快递员的状态或id来查询）
     *
     * @param employee
     * @return
     */
    @Deprecated(since = "查询本网点快递员，应根据网点Id从网点员工关系表内查找到快递员。\n" +
            "请使用StationEmployeeController 的 getEmployees()\n" +
            "网点端中，获取所有员工，在通过过滤的方式，筛选出快递员。")
    @PostMapping("/employee")
    public Result searchCourier(@RequestBody Employee employee) {

        List<StationEmployee> seList = stationService.searchCourier(employee.getStatus(), employee.getId());
        if (seList.isEmpty())
            return Result.error("无匹配结果");
        else {
            Result r = Result.success("查询成功！");
            r.setData(seList);
            return r;
        }
    }

    /**
     * 查看快递员信息（直接调用employeeService中提供的selectById（）查询方法）
     *
     * @param emloyeeId
     * @return
     */
    @Operation(summary = "查看快递员信息", description = "该方法与EmployeeController GET /employee 一致",
            parameters = {
                    @Parameter(name = "employee_id", schema = @Schema(implementation = Long.class)),
            },
            responses = @ApiResponse(description = "返回消息"
            )
    )
    @GetMapping("/employee/info")
    public Result searchCourierInfo(@RequestParam("employee_id") Long emloyeeId) {
        Employee employee = employeeService.selectById(emloyeeId);
        Result r = Result.success("查询成功！");
        r.setData(employee);
        return r;
    }

    /**
     * 撤销快递员
     *
     * @param id
     * @return
     */
    @Operation(summary = "撤销网点员工", description = "根据关系Id删除网点与员工的关系",
            parameters = {
                    @Parameter(name = "id", description = "网点员工关系Id", schema = @Schema(implementation = Long.class)),
            },
            responses = @ApiResponse(description = "返回消息"
            )
    )
    @PostMapping("/employee/delete")
    public Result deleteCourier(@RequestParam("id") Long id) {
        Employee employee = employeeService.selectById(id);
        if (!Objects.isNull(employee)) {
            stationService.deleteCourier(id);
            return Result.success("撤销成功！");
        }
        return Result.error("操作失败！");
    }

    // zgd code
    @PostMapping()
    public Result list() {

        List<Station> stationList = stationService.queryList();
        Result result = Result.success("查询成功");
        result.setData(stationList);
        return result;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Station station) {
        stationService.insert(station);
        Result result = Result.success("查询成功");
        result = list();
        return result;
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody Station station) {
        System.out.println(station);
        int res = stationService.update(station);
        stationService.update(station);

        Result result = Result.success("查询成功");
        result = list();
        return result;
    }

    @PostMapping("/deleteone")
    public Result delete(@RequestBody Long id) {
        boolean num = stationService.deleteById(id);
        System.out.println(id);
        if (num) {
            Result result = Result.success("删除成功");
            result = list();
            return result;
        } else {
            Result result = Result.error("删除失败");
            return result;
        }
    }

    @PostMapping("/deletemany")
    public Result deletemany(@RequestBody List<Long> ids) {
        boolean num = stationService.deleteBatchIds(ids);
        if (num) {
            Result result = Result.success("删除成功");
            result = list();
            return result;
        } else {
            Result result = Result.error("删除失败");
            return result;
        }

    }
}
