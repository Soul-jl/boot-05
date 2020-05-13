package com.jy.mapper.role;

import com.jy.model.role.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface RoleMapper {

    @Select(" select r1.t_name" +
            " from t_roles r1" +
            " right join (select urmid.t_role_id" +
            " from t_u_r_mids urmid" +
            " where urmid.t_user_id = #{userID}) r2" +
            " on r1.t_id = r2.t_role_id")
    Set<String> selectRoleListByUserID(Role role);
}
