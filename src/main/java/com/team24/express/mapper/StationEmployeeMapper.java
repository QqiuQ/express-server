package com.team24.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team24.express.entity.Employee;
import com.team24.express.entity.Station;
import com.team24.express.entity.StationEmployee;
import com.team24.express.entity.vo.EmployeeRoleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @className: StationEmployee
 * @author: Bobby
 * @date: 11/10/2023
 **/
@Mapper
public interface StationEmployeeMapper extends BaseMapper<StationEmployee> {
    List<EmployeeRoleVo> getAdminsById(Long stationId);

    List<EmployeeRoleVo> getEmployeesById(Long stationId);

    Station getStationByEmployeeId(Long employeeId);
}
