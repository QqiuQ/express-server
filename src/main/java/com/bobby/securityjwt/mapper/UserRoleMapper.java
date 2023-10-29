package com.bobby.securityjwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobby.securityjwt.entity.Role;
import com.bobby.securityjwt.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @className: UserRoleMapper
 * @author: Bobby
 * @date: 10/29/2023
 **/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    @Select("select role.* from role, user_role where user_role.user_id=#{userId} and user_role.role_id=role.id and user_role.type=#{type}")
    List<Role> getRolesByIdAndType(Long userId, String type);
    @Select("select role.name from role, user_role where user_role.user_id=#{userId} and user_role.role_id=role.id and user_role.type=#{type}")
    List<String> getRoleNamesByIdAndType(Long userId, String type);

//    List<Role> getRolesByUserName(String username);
//
//    List<String> getRoleNamesByUsername(String username);
}
