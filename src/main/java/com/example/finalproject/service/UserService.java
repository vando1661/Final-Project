package com.example.finalproject.service;

import com.example.finalproject.model.entity.User;
import com.example.finalproject.model.service.UserServiceModel;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    User findById(Long id);

}
