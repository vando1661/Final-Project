package com.example.finalproject.service;

import com.example.finalproject.model.entity.PlanEntity;
import com.example.finalproject.model.enums.PlanEnum;

import java.awt.*;
import java.util.List;

public interface PlanService {

    void initPlan();

    PlanEntity findByPlanEnum(PlanEnum planEnum);

    PlanEntity getPlanById(Long id);


    List<PlanEntity> getAllPlans();

    void savePlan(PlanEntity planEntity);
}


