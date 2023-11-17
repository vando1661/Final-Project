package com.example.finalproject.service.impl;

import com.example.finalproject.model.entity.RoleUserEntity;
import com.example.finalproject.model.enums.RoleUserEnum;
import com.example.finalproject.repository.RoleRepository;
import com.example.finalproject.service.RoleService;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleUserEntity getRoleByName(String name) {
        return this.roleRepository.findByName(RoleUserEnum.valueOf(name));
    }
}
