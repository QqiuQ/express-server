package com.team24.express.service;

import com.team24.express.entity.Station;
import com.team24.express.entity.StationEmployee;
import com.team24.express.entity.vo.EmployeeRoleVo;

import java.util.List;

/**
 * @className: StationEmployeeService
 * @author: Bobby
 * @date: 11/10/2023
 **/
public interface StationEmployeeService {

    boolean add(StationEmployee se);

    List<EmployeeRoleVo> getAdminsById(Long stationId);

    List<EmployeeRoleVo> getEmployeesById(Long stationId);

    Station getStationByAdminId(Long employeeId);
}
