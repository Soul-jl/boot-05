package com.jy.service.role.impl;

import com.jy.mapper.role.RoleMapper;
import com.jy.model.role.Role;
import com.jy.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<String> selectRoleListByUserID(Role role) {

        Set<String> roleSet = roleMapper.selectRoleListByUserID(role);
        return roleSet;
    }
}
