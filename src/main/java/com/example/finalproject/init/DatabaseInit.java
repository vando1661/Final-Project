package com.example.finalproject.init;

import com.example.finalproject.service.PlanService;
import com.example.finalproject.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final RoleService roleService;
    private final PlanService planService;


    public DatabaseInit(RoleService roleService, PlanService planService) {
        this.roleService = roleService;
        this.planService = planService;
    }

    @Override
    public void run(String... args) throws Exception {

        roleService.initRoles();
        planService.initPlan();

    }
}
