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
    import java.util.Optional;

    @Service

    public class PlanServiceImpl implements PlanService {

        private final PlanRepository planRepository;
        private final UserRepository userRepository;

        public PlanServiceImpl(PlanRepository planRepository, UserRepository userRepository) {
            this.planRepository = planRepository;
            this.userRepository = userRepository;
        }

        @Override
        @Transactional
        public void initPlan() {
            if(planRepository.count() == 0) {
                planRepository.saveAll(List.of(
                        new PlanEntity(PlanEnum.BASIC, 29.56, 8, false),
                        new PlanEntity(PlanEnum.STANDARD, 49.56, 12, true),
                        new PlanEntity(PlanEnum.PREMIUM, 69.56, 16, true)
                ));
            }
        }

        @Override
        public PlanEntity findByPlanEnum(PlanEnum planEnum) {
            return planRepository
                    .findByPlan(planEnum)
                    .orElseThrow(() -> new IllegalArgumentException("Plan not found: " + planEnum));
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

//        @Override
//        @Transactional
//        public void savePlan(PlanEntity planEntity) {
//
//            planRepository.save(planEntity);
//        }
//
//        @Override
//        public Optional<PlanEntity> getSelectedPlanForUser(Long userId) {
//
//            return userRepository.findById(userId).map(UserEntity::getPlan);
//        }
        @Override
        public boolean userHasPlan(UserEntity user) {
            return user.getPlan() != null;
        }
        @Override
        public void removePlanFromUser(Long userId) {
            Optional<UserEntity> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                UserEntity userEntity = optionalUser.get();
                userEntity.setPlan(null);
                userRepository.save(userEntity);
            }
        }
    }
