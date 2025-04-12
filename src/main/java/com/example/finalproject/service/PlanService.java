package com.example.finalproject.service;

import com.example.finalproject.model.entity.PlanEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.PlanEnum;

import java.util.List;
import java.util.Optional;

public interface PlanService {

//    void initPlan();
//
//    PlanEntity findByPlanEnum(PlanEnum planEnum);

    PlanEntity getPlanById(Long id);

//    void changeUserPlan(UserEntity user, PlanEntity newPlan);

    List<PlanEntity> getAllPlans();

//    boolean userHasPlan(UserEntity user);
//
//    void removePlanFromUser(Long userId);
//    void resetPlanCredits(Long planId);
//    void changeUserPlan(Long userId, Long newPlanId);
    void savePlan(PlanEntity plan);
    long count();
//    void selectPlan(Long userId, Long planId);


}


