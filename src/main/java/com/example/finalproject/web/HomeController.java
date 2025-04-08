package com.example.finalproject.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller

public class HomeController {

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        return "/home";
    }

    @GetMapping("/about")
    public String about(){
        return "/about";
    }
    @GetMapping("/healthy-food")
            public String healthyFood(){
        return "/healthy-food";
    }
    @GetMapping("/kids-zone")
    public String kidsZone(){
        return "/kids-zone";
    }
    @GetMapping("/tennis")
    public String tennis(){
        return "/tennis";
    }
    @GetMapping("/swimming")
    public String swimming(){
        return "/swimming";
    }
    @GetMapping("/fitness")
    public String fitness(){
        return "fitness";
    }
    @GetMapping("/gymnastics")
    public String gymnastics(){
        return "/gymnastics";
    }

}