package com.team24.express.entity.vo;

import com.team24.express.entity.Employee;
import lombok.Data;

/**
 * @className: EmployeeRoleFullVo
 * @author: Bobby
 * @date: 11/12/2023
 **/
@Data
public class EmployeeRoleFullVo extends Employee {

    private Integer roleId;
    private String roleName;
}
