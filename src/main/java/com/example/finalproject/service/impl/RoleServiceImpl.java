package com.example.finalproject.service.impl;

import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.enums.RoleUserEnum;
import com.example.finalproject.repository.RoleRepository;
import com.example.finalproject.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;

    }

    @Override
    public void initRoles() {

        if(roleRepository.count() != 0){
            return;
        }
        Arrays.stream(RoleUserEnum.values())
                .forEach(roleUserEnum -> {
                    RoleEntity roleEntity = new RoleEntity();
                    roleEntity.setRole(roleUserEnum);
                    roleRepository.save(roleEntity);
                });
    }

    @Override
    public RoleEntity findByRoleUserEnum(RoleUserEnum roleUserEnum) {
        return roleRepository
                .findByRole(roleUserEnum)
                .orElse(null);
    }

    @Override
    public RoleEntity findByRole(RoleUserEnum roleUserEnum) {
        return roleRepository.findByRole(roleUserEnum)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleUserEnum));
    }

}
