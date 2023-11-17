package com.example.finalproject.model.entity;

import jakarta.persistence.*;
import com.example.finalproject.model.enums.RoleUserEnum;


@Entity
@Table(name = "roles")
public class RoleUserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private RoleUserEnum name;

  public RoleUserEntity() {

  }
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RoleUserEnum getName() {
    return name;
  }

  public void setName(RoleUserEnum name) {
    this.name = name;
  }
}