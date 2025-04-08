package com.example.finalproject.init;

import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.RoleUserEnum;
import com.example.finalproject.repository.RoleRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.PlanService;
import com.example.finalproject.service.RoleService;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final RoleService roleService;
    private final PlanService planService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public DatabaseInit(RoleService roleService, PlanService planService, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, PasswordEncoder passwordEncoder1) {
        this.roleService = roleService;
        this.planService = planService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        this.passwordEncoder = passwordEncoder1;
    }

    @Override
    public void run(String... args) throws Exception {

        roleService.initRoles();
        planService.initPlan();

        if (userRepository.count() == 0) {
            System.out.println("There are no users in the database, we are creating the master admin...");

            RoleEntity adminRole = roleRepository.findByRole(RoleUserEnum.ADMIN)
                    .orElseThrow(() -> new IllegalStateException("The ADMIN role was not found!"));

            UserEntity adminUser = new UserEntity();
            adminUser.setUsername("admin");
            adminUser.setName("admin1");
            adminUser.setSurname("admin2");
            adminUser.setEmail("admin@example.com");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setRole(adminRole);
            userRepository.save(adminUser);
            System.out.println("The administrator was created: admin / admin123");

        }
        if (userRepository.count() == 1 ) {
            RoleEntity userRole = roleRepository.findByRole(RoleUserEnum.USER)
                    .orElseThrow(() -> new IllegalStateException("The USER role was not found!"));
            UserEntity user = new UserEntity();
            user.setUsername("user");
            user.setName("user1");
            user.setSurname("user2");
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole(userRole);
            userRepository.save(user);
            System.out.println("The user was created: user / user123");
        }
        if (userRepository.count() == 2 ) {
            RoleEntity userRole = roleRepository.findByRole(RoleUserEnum.ADMIN)
                    .orElseThrow(() -> new IllegalStateException("The ADMIN role was not found!"));
            UserEntity user = new UserEntity();
            user.setUsername("admin2");
            user.setName("admin2");
            user.setSurname("admin2");
            user.setEmail("admin2@example.com");
            user.setPassword(passwordEncoder.encode("admin2"));
            user.setRole(userRole);
            userRepository.save(user);
            System.out.println("The admin was created: admin2 / admin2");
        }
    }
}
