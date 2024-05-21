package com.example.finalproject.config;

import com.example.finalproject.model.entity.PlanEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "selectedPlan")
    public PlanEntity selectedPlan() {
        return new PlanEntity();
    }

}
