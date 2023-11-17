package com.example.finalproject.repository;

import com.example.finalproject.model.entity.RoleUserEntity;
import com.example.finalproject.model.enums.RoleUserEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<RoleUserEntity, Long> {
    RoleUserEntity findByName(RoleUserEnum roleEnum);
}
