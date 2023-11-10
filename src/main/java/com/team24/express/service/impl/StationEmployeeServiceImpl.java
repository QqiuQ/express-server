package com.team24.express.service.impl;

import com.team24.express.entity.Employee;
import com.team24.express.entity.Station;
import com.team24.express.entity.StationEmployee;
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
    public List<Employee> getAdminsById(Long stationId) {

        return stationEmployeeMapper.getAdminsById(stationId);
    }

    @Override
    public List<Employee> getEmployeesById(Long stationId) {
        return stationEmployeeMapper.getEmployeesById(stationId);

    }

    @Override
    public Station getStationByAdminId(Long employeeId) {
        return stationEmployeeMapper.getStationByAdminId(employeeId);
    }
}
