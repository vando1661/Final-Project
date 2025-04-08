package com.example.finalproject.repository;

import com.example.finalproject.model.entity.PlanEntity;
import com.example.finalproject.model.enums.PlanEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Component
@Repository
public interface PlanRepository extends JpaRepository<PlanEntity,Long> {

    Optional<PlanEntity> findByPlan(PlanEnum planEnum);


}
