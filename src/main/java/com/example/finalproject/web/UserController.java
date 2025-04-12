package com.example.finalproject.web;

import com.example.finalproject.model.dto.bainding.UserLoginBindingModel;
import com.example.finalproject.model.dto.bainding.UserProfileUpdateModel;
import com.example.finalproject.model.dto.bainding.UserRegisterBindingModel;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.serviceModel.UserServiceModel;
import com.example.finalproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;

    }

    @GetMapping("/edit-profile")
    public String editProfile(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("user", user);
        model.addAttribute("userProfileUpdateModel", new UserProfileUpdateModel());
        return "edit-profile";
    }

    @PostMapping("/edit-profile")
    public String updateProfile(@Valid @ModelAttribute("userProfileUpdateModel") UserProfileUpdateModel userProfileUpdateModel, Model model) {


        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        user.setName(userProfileUpdateModel.getName());
        user.setSurname(userProfileUpdateModel.getSurname());
        user.setEmail(userProfileUpdateModel.getEmail());

        if (userProfileUpdateModel.getPassword() != null && !userProfileUpdateModel.getPassword().isEmpty()) {
            if (!userProfileUpdateModel.getPassword().equals(userProfileUpdateModel.getConfirmPassword())) {
                model.addAttribute("errorMessage", "Passwords do not match.");
                model.addAttribute("userProfileUpdateModel", userProfileUpdateModel);
                return "edit-profile";
            }
            user.setPassword(userProfileUpdateModel.getPassword());
        }

        userService.saveUser(user);

        model.addAttribute("message", "Profile updated successfully!");
        return "profile";
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        userService.registerUser(modelMapper
                .map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:login";

    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }
        return "login";
    }
    @PostMapping("/login")
    public String loginConfirm(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/users/login";
        }

        if (userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin-dashboard";
        }
        return "redirect:/profile";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }
}
