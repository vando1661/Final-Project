package com.example.finalproject.service.impl;

import com.example.finalproject.model.entity.PlanEntity;
import com.example.finalproject.model.enums.PlanEnum;
import com.example.finalproject.repository.PlanRepository;
import com.example.finalproject.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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
                        case BASIC:
                        case STANDARD:
                        case PREMIUM:
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
    @Override
    public PlanEntity getPlanById(Long id) {
        return planRepository.findById(id)
                .orElse(null);
    }
//@Override
//    public UserEntity getUserByUsername(String username) {
//        return userRepository.findByUsername(username).orElse(null);
//    }
    @Override
    public PlanEntity getPlanEntityDetails() {
        PlanEntity planEntity = new PlanEntity();
        planEntity.setId(planEntity.getId());
        planEntity.setPlan(planEntity.getPlan());
        planEntity.setPrice(planEntity.getPrice());
        planEntity.setCredits(planEntity.getCredits());
        planEntity.setHatKidsZone(planEntity.isHatKidsZone());
        return planEntity;

    }

    @Override
    public void savePlan(PlanEntity planEntity) {
        planRepository.save(planEntity);
    }


}
