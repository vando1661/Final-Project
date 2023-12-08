package com.example.finalproject.web;

import com.example.finalproject.model.entity.PlanEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.PlanEnum;
import com.example.finalproject.repository.PlanRepository;
import com.example.finalproject.sec.CurrentUser;
import com.example.finalproject.service.PlanService;
import com.example.finalproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/users")

public class PlanController {


    private final UserService userService;
    private final CurrentUser currentUser;
    private final PlanService planService;
    private final PlanRepository planRepository;



    public PlanController(UserService userService, CurrentUser currentUser, PlanService planService, PlanRepository planRepository) {
        this.userService = userService;
        this.currentUser = currentUser;
        this.planService = planService;
        this.planRepository = planRepository;
    }

    @GetMapping("/plans")
    public String plans(Model model){


        List<PlanEnum> list = Arrays.asList(PlanEnum.values());

        model.addAttribute("currentUser",currentUser.getUsername());
        model.addAttribute("list",list);
        return "plans";
    }

    @PostMapping("/savePlan")
    public  String savePlan(@RequestParam("userId")Long id,@RequestParam("plansId") Long plansId){
        Long userLogged = currentUser.getId();
        PlanEntity plan = planService.getPlanById(plansId);

        planService.savePlan(plan);
        return "redirect:/profile";
    }
    @GetMapping("/profile")
    public String profile(Model model){

        String userEmail = currentUser.getEmail();
        model.addAttribute("userEmail", userEmail);
        if (!model.containsAttribute("isPlan")) {
            currentUser.setPlan(false);
            return "/profile";
        }
        return "plans";
    }
}
