package com.example.finalproject.init;

import com.example.finalproject.model.entity.PlanEntity;
import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.entity.TrainingEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.DayOfWeek;
import com.example.finalproject.model.enums.PlanEnum;
import com.example.finalproject.model.enums.TypesOfSessions;
import com.example.finalproject.model.enums.RoleUserEnum;
import com.example.finalproject.repository.RoleRepository;
import com.example.finalproject.repository.TrainingRepository;
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
    private final TrainingRepository trainingRepository;

    public DatabaseInit(RoleService roleService, PlanService planService, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, PasswordEncoder passwordEncoder1, TrainingRepository trainingRepository) {
        this.roleService = roleService;
        this.planService = planService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        this.passwordEncoder = passwordEncoder1;
        this.trainingRepository = trainingRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        roleService.initRoles();
        initPlans();

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
        if (userRepository.count() == 1) {
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
        if (userRepository.count() == 2) {
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

        if (trainingRepository.count() == 0) {

            UserEntity user = userRepository.findByUsername("user")
                    .orElseThrow(() -> new IllegalStateException("User not found"));


            DayOfWeek[] days = {DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                    DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY};

            for (DayOfWeek day : days) {

                TrainingEntity swimming = new TrainingEntity();
                swimming.setDay(day);
                swimming.setTrainingType(TypesOfSessions.SWIMMING);
                swimming.setStatus(false);
                trainingRepository.save(swimming);


                TrainingEntity tennis = new TrainingEntity();
                tennis.setDay(day);
                tennis.setTrainingType(TypesOfSessions.TENNIS);
                tennis.setStatus(false);
                trainingRepository.save(tennis);


                TrainingEntity gymnastics = new TrainingEntity();
                gymnastics.setDay(day);
                gymnastics.setTrainingType(TypesOfSessions.GYMNASTICS);
                gymnastics.setStatus(false);
                trainingRepository.save(gymnastics);
            }

        }

    }
    private void initPlans() {
        if (planService.count() == 0) {
            PlanEntity basicPlan = new PlanEntity(
                    PlanEnum.BASIC,
                    29.96,
                    8,
                    false
            );
            basicPlan.setInitialCredits(8);

            PlanEntity standardPlan = new PlanEntity(
                    PlanEnum.STANDARD,
                    69.96,
                    12,
                    true
            );
            standardPlan.setInitialCredits(12);

            PlanEntity premiumPlan = new PlanEntity(
                    PlanEnum.PREMIUM,
                    89.96,
                    16,
                    true
            );
            premiumPlan.setInitialCredits(16);

            planService.savePlan(basicPlan);
            planService.savePlan(standardPlan);
            planService.savePlan(premiumPlan);

        }
    }
}