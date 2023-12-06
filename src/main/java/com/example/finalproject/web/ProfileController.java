package com.example.finalproject.web;

import com.example.finalproject.sec.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class ProfileController {
    private final CurrentUser currentUser;

    public ProfileController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }



    @GetMapping("/plan")
    public String index(){
        return "/plan";
    }
    @GetMapping("/profile")
    public String profile(Model model){
        if (!model.containsAttribute("isPlan")) {
            currentUser.setPlan(false);
            return "/profile";
        }
        return "/plan";
    }
//    @PostMapping("/add")
//    public String addConfirm(@Valid OrderAddBindingModel orderAddBindingModel
//            , BindingResult bindingResult, RedirectAttributes redirectAttributes){
//
//        if(bindingResult.hasErrors()){
//            redirectAttributes.addFlashAttribute("orderAddBindingModel",orderAddBindingModel);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel",bindingResult);
//
//            return "redirect:add";
//        }
//
//        orderService.addOrder(modelMapper
//                .map(orderAddBindingModel, OrderServiceModel.class));
//
//        return "redirect:/";
//
//    }
}
