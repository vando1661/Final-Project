package com.example.finalproject.web;

import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.TrainingService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class TrainingController {
    private final UserRepository userRepository;
    private final TrainingService trainingService;

    public TrainingController(UserRepository userRepository, TrainingService trainingService) {
        this.userRepository = userRepository;
        this.trainingService = trainingService;
    }

    @PostMapping("/trainings/{id}/complete")
    public String completeTraining(@PathVariable Long id, Principal principal) {
        UserEntity user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        trainingService.completeTraining(id, user.getId());
        return "redirect:/trainings";
    }

    @PostMapping("/trainings/{id}/cancel-completed")
    public String cancelCompletedTraining(@PathVariable Long id, Principal principal) {
        UserEntity user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        trainingService.cancelCompletedTraining(id, user.getId());
        return "redirect:/trainings";
    }
}
