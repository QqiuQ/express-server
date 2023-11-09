package com.team24.express.controller;

import com.team24.express.common.Result;
import com.team24.express.entity.Employee;
import com.team24.express.entity.Order;
import com.team24.express.entity.StationEmployee;
import com.team24.express.entity.StationOrder;
import com.team24.express.service.EmployeeService;
import com.team24.express.service.StaitonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
     * @param order
     * @return
     */
    @PostMapping("/order")
    public Result selectPackages(@RequestBody Order order){
        List<Order> orderList = stationService.selectPackages(order);
        if(orderList.isEmpty())
            return Result.error("未查询到数据！");
        else{
            Result r =Result.success("查询成功！");
            r.setData(orderList);
            return r;
        }
    }


    /**
     * 快递入库（订单与网点关系表中新增）
     * @param stationOrder
     * @return
     */
    @PostMapping("/order/add")
    public Result packageInRep(@RequestBody StationOrder stationOrder){
        stationService.packageInRep(stationOrder);
        return Result.success("快递入库成功！");
    }

    /**
     * 快递出库（订单与网点关系表中修改订单的状态为已出库）
     * @param stationOrder
     * @return
     */
    @PostMapping("/order/remove")
    public Result packageOutRep(@RequestBody StationOrder stationOrder){
        stationService.packageOutRep(stationOrder);
        return Result.success("快递出库成功！");
    }

    /**
     * 录入快递员（先在employee查询是否存在该员工，若存在将其信息加入网点员工表中）
     * @param employeeId
     * @param stationId
     * @return
     */
    @PostMapping("/employee/add")
    public Result addNewCourier(@RequestParam Long employeeId, @RequestParam Long stationId){
        Employee courier = employeeService.selectById(employeeId);
        if(Objects.isNull(courier))
            return Result.error("不存在该员工");
        else{
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

    /**
     * 根据员工实体查询本网点的快递员（通过匹配快递员的状态或id来查询）
     * @param employee
     * @return
     */
    @PostMapping("/employee")
    public Result searchCourier(@RequestBody Employee employee){
        List<StationEmployee> seList = stationService.searchCourier(employee.getStatus(),employee.getId());
        if(seList.isEmpty())
            return Result.error("无匹配结果");
        else{
            Result r =Result.success("查询成功！");
            r.setData(seList);
            return r;
        }
    }

    /**
     * 查看快递员信息（直接调用employeeService中提供的selectById（）查询方法）
     * @param emloyeeId
     * @return
     */
    @GetMapping("/employee/info")
    public Result searchCourierInfo(@RequestParam("employee_id") Long emloyeeId){
        Employee employee = employeeService.selectById(emloyeeId);
        Result r = Result.success("查询成功！");
        r.setData(employee);
        return  r;
    }

    /**
     * 撤销快递员
     * @param id
     * @return
     */
    @PostMapping("/employee/delete")
    public Result deleteCourier(@RequestParam("id") Long id){
        Employee employee = employeeService.selectById(id);
        if(!Objects.isNull(employee)){
            stationService.deleteCourier(id);
            return Result.success("撤销成功！");
        }
        return Result.error("操作失败！");
    }
}
