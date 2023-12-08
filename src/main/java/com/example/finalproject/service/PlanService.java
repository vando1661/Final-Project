package com.example.finalproject.service;

import com.example.finalproject.model.entity.PlanEntity;
import com.example.finalproject.model.enums.PlanEnum;

public interface PlanService {

    void initPlan();

    PlanEntity findByPlanEnum(PlanEnum planEnum);

    PlanEntity getPlanById(Long id);

    PlanEntity getPlanEntityDetails();


    void savePlan(PlanEntity planEntity);
}


