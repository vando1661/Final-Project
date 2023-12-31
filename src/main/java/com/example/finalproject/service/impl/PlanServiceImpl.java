package com.example.finalproject.service.impl;

import com.example.finalproject.model.entity.PlanEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.PlanEnum;
import com.example.finalproject.repository.PlanRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.PlanService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    private final UserRepository userRepository;

    public PlanServiceImpl(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void initPlan() {
        if(planRepository.count() == 0) {
            PlanEntity planEntity = new PlanEntity();
            planEntity.setPlan(PlanEnum.BASIC);
            planEntity.setPrice(29.56);
            planEntity.setCredits(8);
            planEntity.setHatKidsZone(false);
            this.planRepository.save(planEntity);

            PlanEntity planEntity2 = new PlanEntity();
            planEntity2.setPlan(PlanEnum.STANDARD);
            planEntity2.setPrice(49.56);
            planEntity2.setCredits(12);
            planEntity2.setHatKidsZone(true);
            this.planRepository.save(planEntity2);

            PlanEntity planEntity3 = new PlanEntity();
            planEntity3.setPlan(PlanEnum.PREMIUM);
            planEntity3.setPrice(69.56);
            planEntity3.setCredits(16);
            planEntity3.setHatKidsZone(true);
            this.planRepository.save(planEntity3);
        }
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
                .orElseThrow(() -> new IllegalArgumentException("Invalid planId: " + id));
    }

    @Override
    public List<PlanEntity> getAllPlans() {
        return planRepository.findAll();
    }

    @Override
    public void savePlan(PlanEntity planEntity) {

        planRepository.save(planEntity);
    }

    @Override
    public PlanEntity getSelectedPlanForUser(Long userId) {

        return userRepository.findById(userId)
                .map(UserEntity::getPlan)
                .orElse(null);

    }
}
