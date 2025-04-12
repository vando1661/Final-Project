package com.example.finalproject.repository;

import com.example.finalproject.model.entity.TrainingEntity;
import com.example.finalproject.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity,Long> {
    List<TrainingEntity> findByUser(UserEntity user);

    @Modifying
    @Query("DELETE FROM TrainingEntity t WHERE t.user = :user")
    void deleteAllByUser(@Param("user") UserEntity user);

}
