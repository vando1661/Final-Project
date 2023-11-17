package com.example.finalproject.service;


import com.example.finalproject.model.entity.RoleUserEntity;

public interface RoleService {
    RoleUserEntity getRoleByName(String name);
}
