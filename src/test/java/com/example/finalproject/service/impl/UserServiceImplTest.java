package com.example.finalproject.service.impl;

import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.RoleUserEnum;
import com.example.finalproject.model.serviceModel.UserServiceModel;
import com.example.finalproject.repository.RoleRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    @MockBean
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;
    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    @BeforeEach
    void setUp() {
        UserRepository userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        modelMapper = new ModelMapper();

        userService = new UserServiceImpl(userRepository, modelMapper, roleRepository, passwordEncoder);
    }

    @Test
    void registerUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("john");
        userEntity.setPassword("password");

        RoleEntity role = new RoleEntity();
        role.setRole(RoleUserEnum.ADMIN);

        when(userRepository.count()).thenReturn(0L);
        when(roleRepository.findByRole(RoleUserEnum.ADMIN)).thenReturn(Optional.of(role));
        when(passwordEncoder.encode("password")).thenReturn("encodedPass");

        when(userRepository.save(any(UserEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

    }


//    @Test
//    void findByUsername() {
//        UserEntity userEntity = new UserEntity();
//
//        Optional<UserEntity> result = userService.findByUsername("testuser");
//
//        User user = new User("testuser", "password123");
//        userService.saveUser("s");
//        assertNotNull(result);
//        assertEquals("testuser", result);
//   }

//    @Test
//    void saveUser() {
//    }
//
//    @Test
//    void getAllUsers() {
//    }
//
//    @Test
//    void deleteUser() {
//    }
//
//    @Test
//    void findById() {
//    }
    }