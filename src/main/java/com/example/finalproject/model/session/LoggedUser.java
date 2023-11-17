//package com.example.finalproject.model.session;
//
//import com.example.finalproject.model.entity.RoleUserEntity;
//import com.example.finalproject.model.enums.RoleUserEnum;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class LoggedUser {
//
//    private String username;
//    private Set<RoleUserEntity> roles;
//    private boolean isLogged;
//
//    public LoggedUser() {
//        this.roles = new HashSet<>();
//    }
//
//    public void reset() {
//        this
//                .setUsername(null)
//                .setRoles(Collections.emptySet())
//                .setLogged(false);
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public LoggedUser setUsername(String username) {
//        this.username = username;
//        return this;
//    }
//
//    public Set<RoleUserEntity> getRoles() {
//        return roles;
//    }
//
//    public LoggedUser setRoles(Set<RoleUserEntity> roles) {
//        this.roles = roles;
//        return this;
//    }
//
//    public boolean isLogged() {
//        return isLogged;
//    }
//
//    public LoggedUser setLogged(boolean logged) {
//        isLogged = logged;
//        return this;
//    }
//
//    public boolean isAdmin() {
//        return this.roles.stream()
//                .anyMatch(role -> role.getName().equals(RoleUserEnum.ADMIN));
//    }
//}
