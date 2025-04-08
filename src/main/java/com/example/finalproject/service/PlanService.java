package com.example.finalproject.service;

import com.example.finalproject.model.entity.PlanEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.PlanEnum;

import java.util.List;
import java.util.Optional;

public interface PlanService {

    void initPlan();

    PlanEntity findByPlanEnum(PlanEnum planEnum);

    PlanEntity getPlanById(Long id);


    List<PlanEntity> getAllPlans();

//    void savePlan(PlanEntity planEntity);
//
//    Optional<PlanEntity> getSelectedPlanForUser(Long id);
    boolean userHasPlan(UserEntity user);

    void removePlanFromUser(Long userId);

}


