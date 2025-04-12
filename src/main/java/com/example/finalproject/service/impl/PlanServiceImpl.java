    package com.example.finalproject.service.impl;

    import com.example.finalproject.model.entity.PlanEntity;
    import com.example.finalproject.repository.PlanRepository;
    import com.example.finalproject.service.PlanService;
    import org.springframework.stereotype.Service;
    import java.util.List;

    @Service
    public class PlanServiceImpl implements PlanService {

        private final PlanRepository planRepository;

        public PlanServiceImpl(PlanRepository planRepository) {
            this.planRepository = planRepository;
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
        public void savePlan(PlanEntity plan) {
            planRepository.save(plan);
        }

        @Override
        public long count() {
            return planRepository.count();
        }

    }
