package com.example.finalproject.web;

import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.service.PlanService;
import com.example.finalproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class AdminController {

    private final UserService userService;
    private final PlanService planService;

    public AdminController(UserService userService, PlanService planService) {
        this.userService = userService;
        this.planService = planService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin-dashboard")
    public String adminDashboard(Model model) {
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "/admin-dashboard";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/remove-plan/{userId}")
    public String removeUserPlan(@PathVariable Long userId,RedirectAttributes redirectAttributes) {
        planService.removePlanFromUser(userId);
        redirectAttributes.addFlashAttribute("message", "Plan deleted successfully");
        return "redirect:/users/admin-dashboard";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam Long userIdToDelete, Authentication authentication, HttpServletRequest request, RedirectAttributes redirectAttributes) throws ServletException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long currentUserId = userService.findByUsername(userDetails.getUsername()).get().getId();

        try {

            userService.deleteUser(currentUserId, userIdToDelete);
        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/users/admin-dashboard";
        }


        if (currentUserId.equals(userIdToDelete)) {
            request.logout();
            return "redirect:/users/home";
        }

        redirectAttributes.addFlashAttribute("message", "User deleted successfully");
        return "redirect:/users/admin-dashboard";
    }
}
