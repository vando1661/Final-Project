package com.example.finalproject.service.impl;

import com.example.finalproject.model.entity.PlanEntity;
import com.example.finalproject.model.enums.PlanEnum;
import com.example.finalproject.repository.PlanRepository;
import com.example.finalproject.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public void initPlan() {

        if(this.planRepository.count() != 0){
            return;
        }
        Arrays.stream(PlanEnum.values())
                .forEach(plan -> {
                    PlanEntity planEntity = new PlanEntity();
                    planEntity.setPlan(plan);
                    switch (plan){
                        case BASIC -> planEntity.setCredit(8);
                        case STANDARD -> planEntity.setCredit(12);
                        case PREMIUM -> planEntity.setCredit(16);
                    }
                    this.planRepository.save(planEntity);
                });
    }

    @Override
    public PlanEntity findByPlanEnum(PlanEnum planEnum) {
        return planRepository
                .findByPlan(planEnum)
                .orElse(null);
    }
}
