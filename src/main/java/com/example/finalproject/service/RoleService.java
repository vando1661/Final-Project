package com.example.finalproject.service;

import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.enums.RoleUserEnum;

public interface RoleService {

    void initRoles();

//    RoleEntity findByRoleUserEnum(RoleUserEnum roleUserEnum);

    RoleEntity findByRole(RoleUserEnum roleUserEnum);
}
