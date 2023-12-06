package com.example.finalproject.model.entity;

import com.example.finalproject.model.enums.RoleUserEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleUserEnum role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleUserEnum getRole() {
        return role;
    }

    public void setRole(RoleUserEnum role) {
        this.role = role;
    }
}
