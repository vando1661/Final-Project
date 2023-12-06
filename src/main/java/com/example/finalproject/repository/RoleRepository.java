package com.example.finalproject.repository;

import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.enums.RoleUserEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    Optional<RoleEntity>findByRole(RoleUserEnum roleUserEnum);
}
