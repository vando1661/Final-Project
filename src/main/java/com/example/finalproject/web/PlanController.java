package com.example.finalproject.web;

import com.example.finalproject.model.entity.PlanEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.service.PlanService;
import com.example.finalproject.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")

public class PlanController {


    private final UserService userService;
    private final PlanService planService;


    public PlanController(UserService userService, PlanService planService) {
        this.userService = userService;
        this.planService = planService;

    }

    @GetMapping("/users/plans")
    public String showPlans(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        Optional<UserEntity> userOptional = userService.findByUsername(userDetails.getUsername());

        if (userOptional.isEmpty()) {
            return "redirect:/users/login";
        }

        UserEntity user = userOptional.get();

        if (user.getPlan() != null) {
            return "redirect:/users/profile";
        }

        List<PlanEntity> list = planService.getAllPlans();
        model.addAttribute("list", list);
        model.addAttribute("hasPlan", user.getPlan() != null);
        return "plans";

    }

    @PostMapping("/users/savePlan")
    @Transactional
    public String savePlan(@RequestParam("planId") Long planId,
                           @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        UserEntity user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        PlanEntity selectedPlan = planService.getPlanById(planId);

        if (selectedPlan == null) {
            return "redirect:/plans";
        }

        user.setPlan(selectedPlan);
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("planSelected", true);

        return "redirect:/users/profile";
    }

    @GetMapping("/users/profile")
    public String showProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<UserEntity> user = userService.findByUsername(userDetails.getUsername());

        if (user.isEmpty()) {
            return "redirect:/users/login";
        }

        UserEntity userE = user.get()   ;
        PlanEntity selectedP = userE.getPlan();

        model.addAttribute("selectedP", selectedP);

        return "profile";
    }

}
