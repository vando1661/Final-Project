package com.example.finalproject.web;

import com.example.finalproject.model.dto.bainding.UserProfileUpdateModel;
import com.example.finalproject.model.entity.PlanEntity;
import com.example.finalproject.model.entity.RoleEntity;
import com.example.finalproject.model.entity.TrainingEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.DayOfWeek;
import com.example.finalproject.model.enums.RoleUserEnum;
import com.example.finalproject.model.enums.TypesOfSessions;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.RoleService;
import com.example.finalproject.service.TrainingService;
import com.example.finalproject.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/")

public class ProfileController {

    private final UserService userService;
    private final RoleService roleService;
    private final TrainingService trainingService;
    private final UserRepository userRepository;

    public ProfileController(UserService userService, RoleService roleService, TrainingService trainingService, UserRepository userRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.trainingService = trainingService;
        this.userRepository = userRepository;
    }

    @GetMapping("/users/profile")
    public String showProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<UserEntity> userOp = userService.findByUsername(userDetails.getUsername());

        if (userOp.isEmpty()) {
            return "redirect:/users/login";
        }

        UserEntity user = userOp.get();
        PlanEntity selectedP = user.getPlan();

        boolean hasPlan = selectedP != null;
        model.addAttribute("hasPlan", hasPlan);
        List<TrainingEntity> userTrainings = trainingService.findTrainingsByUser(user);

        Map<DayOfWeek, List<TrainingEntity>> availableTrainings = trainingService.getAllTrainingsGroupedByDay();
        if (hasPlan) {
            model.addAttribute("selectedP", selectedP);
        }
        model.addAttribute("user", user);
        model.addAttribute("userTrainings", userTrainings);
        model.addAttribute("availableTrainings", availableTrainings);
        model.addAttribute("days", DayOfWeek.values());
        model.addAttribute("trainingTypes", TypesOfSessions.values());
        return "profile";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/edit-role/{id}")
    public String editRoleForm(@PathVariable("id") Long userId, Model model, @AuthenticationPrincipal UserDetails userDetails) {

        return getStringCheckingIsAdmin(userId, model, userDetails);
    }

    @PostMapping("/training/select")
    public String selectTraining(
            @RequestParam DayOfWeek day,
            @RequestParam TypesOfSessions trainingType,
            @AuthenticationPrincipal UserDetails userDetails,
            RedirectAttributes redirectAttributes) {

        Optional<UserEntity> userOpt = userService.findByUsername(userDetails.getUsername());
        if (userOpt.isEmpty()) {
            return "redirect:/users/login";
        }

        UserEntity user = userOpt.get();

        try {
            trainingService.assignTrainingToUser(day, trainingType, user);
            redirectAttributes.addFlashAttribute("successMessage", "Training selected successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/users/profile";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users/edit-role/{id}")
    public String editRole(@PathVariable("id") Long userId,
                           @RequestParam("role") RoleUserEnum roleUserEnum,
                           @AuthenticationPrincipal UserDetails userDetails,
                           RedirectAttributes redirectAttributes) {
        if (userDetails.getUsername().equals("admin") && userId == 1) {
            return "redirect:/users/admin-dashboard";
        }

        Optional<UserEntity> userOpt = userService.findById(userId);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            RoleEntity role = roleService.findByRole(roleUserEnum);
            user.setRole(role);
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("message1", "User role changed successfully!");
            return "redirect:/users/admin-dashboard";
        }

        return "redirect:/users/admin-dashboard";
    }

    @PostMapping("/training/remove")
    public String removeTraining(
            @RequestParam Long trainingId,
            @AuthenticationPrincipal UserDetails userDetails,
            RedirectAttributes redirectAttributes) {

        Optional<UserEntity> userOpt = userService.findByUsername(userDetails.getUsername());
        if (userOpt.isEmpty()) {
            return "redirect:/users/login";
        }

        try {
            trainingService.removeTrainingFromUser(trainingId, userOpt.get());
            redirectAttributes.addFlashAttribute("successMessage", "Training removed successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/users/profile";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/edit-role/{id}/role")
    public String editRoleForm(@PathVariable("id") Long userId, Model model, @AuthenticationPrincipal UserDetails userDetails, RoleUserEnum roleUserEnum) {

        return getStringCheckingIsAdmin(userId, model, userDetails);
    }

    private String getStringCheckingIsAdmin(@PathVariable("id") Long userId, Model model,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails.getUsername().equals("admin") && userId == 1) {
            return "redirect:/users/admin-dashboard";
        }

        Optional<UserEntity> userOpt = userService.findById(userId);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            model.addAttribute("user", user);
            model.addAttribute("roles", RoleUserEnum.values());
            return "edit-role";
        }

        return "redirect:/users/admin-dashboard";
    }

    @GetMapping("/users/edit-profile")
    public String editProfile(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("user", user);
        model.addAttribute("userProfileUpdateModel", new UserProfileUpdateModel());
        return "edit-profile";
    }
}
