package com.example.finalproject.service.impl;

import com.example.finalproject.model.entity.PlanEntity;
import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.RoleUserEnum;
import com.example.finalproject.model.service.UserServiceModel;
import com.example.finalproject.repository.RoleRepository;
import com.example.finalproject.repository.UserRepository;

import com.example.finalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        UserEntity userEntity = modelMapper.map(userServiceModel, UserEntity.class);

        String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);

        RoleEntity role;
        if (userRepository.count() == 0) {
            role = roleRepository.findByRole(RoleUserEnum.ADMIN)
                    .orElseThrow(() -> new IllegalArgumentException("Role ADMIN not found"));
        } else {
            role = roleRepository.findByRole(RoleUserEnum.USER)
                    .orElseThrow(() -> new IllegalArgumentException("Role USER not found"));
        }
        userEntity.setRole(role);
        return modelMapper.map(userRepository.save(userEntity), UserServiceModel.class);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


//    @Override
//    public UserEntity getUserById(Long userId) {
//        return userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid userId: " + userId));
//    }

    @Override
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long currentUserId, Long userIdToDelete) {

        if (userIdToDelete.equals(1L)) {
            throw new IllegalArgumentException("The main admin cannot be deleted.");
        }
        Optional<UserEntity> optionalUser = userRepository.findById(userIdToDelete);
        optionalUser.ifPresent(userRepository::delete);

}
}
