package com.jy.service.role;

import com.jy.model.role.Role;

import java.util.Set;

public interface RoleService {
    Set<String> selectRoleListByUserID(Role role);

}
