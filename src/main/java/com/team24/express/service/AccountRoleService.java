package com.team24.express.service;

import com.team24.express.entity.AccountRole;
import com.team24.express.entity.vo.EmployeeRoleVo;

import java.util.List;

/**
 * @className: UserRoleService
 * @author: Bobby
 * @date: 10/29/2023
 **/
public interface AccountRoleService {
    List<String> getRoleNamesByIdAndType(Long userId, String accountType);

    List<EmployeeRoleVo> getEmployeeVoListByRoleName(String roleName);

    List<EmployeeRoleVo> getEmployeeVoListByRoleId(Integer id);

    boolean changeRole(Long employeeId, Integer curRoleId, Integer newRoleId);

    boolean setRole(Long employeeId, Integer roleId);

    AccountRole selectByAccountIdAndRoleId(Long accountId, Integer roleId);

    boolean add(AccountRole accountRole);
}
