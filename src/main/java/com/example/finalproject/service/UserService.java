package com.example.finalproject.service;

import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.serviceModel.UserServiceModel;
import java.util.List;
import java.util.Optional;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    Optional<UserEntity> findByUsername(String username);

    void saveUser(UserEntity user);

    List<UserEntity> getAllUsers();

    void deleteUser(Long userId, Long userIdToDelete);
    Optional<UserEntity> findById(Long id);








}
