package com.example.finalproject.web;

import com.example.finalproject.model.entity.PlanEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.sec.CurrentUser;
import com.example.finalproject.service.PlanService;
import com.example.finalproject.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")

public class PlanController {


    private final UserService userService;
    private final CurrentUser currentUser;
    private final PlanService planService;


    public PlanController(UserService userService, CurrentUser currentUser, PlanService planService) {
        this.userService = userService;
        this.currentUser = currentUser;
        this.planService = planService;

    }

    @GetMapping("/plans")
    public String showPlans(Model model) {

        List<PlanEntity> list = planService.getAllPlans();

        model.addAttribute("currentUser", currentUser.getId());
        model.addAttribute("list", list);
        return "plans";
    }

    @PostMapping("/savePlan")
    @Transactional
    public String savePlan(@RequestParam("userId") Long userId, @RequestParam("planId") Long planId,Model model) {

        UserEntity user = userService.getUserById(userId);
        PlanEntity selectedPlan  = planService.getPlanById(planId);

        user.setPlan(selectedPlan);
        userService.saveUser(user);
        return "redirect:profile";

    }

    @GetMapping("/profile")
    public String showProfile(Model model) {

        PlanEntity selectedPlan = planService.getSelectedPlanForUser(currentUser.getId());
        model.addAttribute("selectedPlan",selectedPlan);
        return "profile";
    }

}
