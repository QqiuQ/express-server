package com.team24.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team24.express.entity.Employee;
import com.team24.express.entity.StationEmployee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @className: StationEmployee
 * @author: Bobby
 * @date: 11/10/2023
 **/
@Mapper
public interface StationEmployeeMapper extends BaseMapper<StationEmployee> {
    List<Employee> getAdminsById(Long stationId);

    List<Employee> getEmployeesById(Long stationId);
}
