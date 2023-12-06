package com.example.finalproject.service.impl;

import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.enums.RoleUserEnum;
import com.example.finalproject.repository.RoleRepository;
import com.example.finalproject.sec.CurrentUser;
import com.example.finalproject.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final CurrentUser currentUser;

    public RoleServiceImpl(RoleRepository roleRepository, CurrentUser currentUser) {
        this.roleRepository = roleRepository;
        this.currentUser = currentUser;
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
//                    if(currentUser.getId() == 1){
//                        roleEntity.setRole(RoleUserEnum.ADMIN);
//                    }else {
//                        roleEntity.setRole(RoleUserEnum.USER);
//                    }
                    roleRepository.save(roleEntity);
                });
    }

    @Override
    public RoleEntity findByRoleUserEnum(RoleUserEnum roleUserEnum) {
        return roleRepository
                .findByRole(roleUserEnum)
                .orElse(null);
    }
}
