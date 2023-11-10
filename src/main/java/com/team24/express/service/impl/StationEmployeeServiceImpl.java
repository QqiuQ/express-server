package com.team24.express.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team24.express.entity.Employee;
import com.team24.express.entity.Station;
import com.team24.express.entity.StationEmployee;
import com.team24.express.entity.vo.EmployeeRoleVo;
import com.team24.express.mapper.StationEmployeeMapper;
import com.team24.express.service.StationEmployeeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @className: StationEmployeeServiceImpl
 * @author: Bobby
 * @date: 11/10/2023
 **/
@Service
public class StationEmployeeServiceImpl implements StationEmployeeService {
    @Resource
    StationEmployeeMapper stationEmployeeMapper;

    @Override
    public boolean add(StationEmployee se) {
        se.setCreateTime(LocalDateTime.now());
        se.setStatus(Employee.STATUS_POSITION_ON);
        return stationEmployeeMapper.insert(se) > 0;
    }

    @Override
    public List<EmployeeRoleVo> getAdminsById(Long stationId) {

        return stationEmployeeMapper.getAdminsById(stationId);
    }

    @Override
    public List<EmployeeRoleVo> getEmployeesById(Long stationId) {
        return stationEmployeeMapper.getEmployeesById(stationId);

    }

    @Override
    public Station getStationByEmployeeId(Long employeeId) {
        return stationEmployeeMapper.getStationByEmployeeId(employeeId);
    }

    @Override
    public StationEmployee getByEmployeeId(Long employeeId) {
        QueryWrapper<StationEmployee> wrapper = new QueryWrapper<>();
        wrapper.eq("employee_id", employeeId);
        return stationEmployeeMapper.selectOne(wrapper);
    }

    @Override
    public List<StationEmployee> getByStationId(Long stationId) {
        QueryWrapper<StationEmployee> wrapper = new QueryWrapper<>();
        wrapper.eq("station_id", stationId);
        return stationEmployeeMapper.selectList(wrapper);
    }

}
