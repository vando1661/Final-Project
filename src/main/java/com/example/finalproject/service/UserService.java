package com.example.finalproject.service;

import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.service.UserServiceModel;
import org.apache.catalina.User;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    UserEntity findById(Long id);


    UserEntity getUserByUsername(String username);

    UserEntity getUserById(Long userId);

    void saveUser(UserEntity user);

    boolean userHasPlan(UserEntity user);



}
