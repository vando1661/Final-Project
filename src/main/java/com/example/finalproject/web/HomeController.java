package com.example.finalproject.web;

import com.example.finalproject.sec.CurrentUser;
import com.example.finalproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, UserService userService) {
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home() {
        return "/index";
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