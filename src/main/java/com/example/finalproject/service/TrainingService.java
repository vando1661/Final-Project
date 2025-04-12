package com.example.finalproject.service;


import com.example.finalproject.model.entity.TrainingEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.DayOfWeek;
import com.example.finalproject.model.enums.TypesOfSessions;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TrainingService {

    void assignTrainingToUser(DayOfWeek day, TypesOfSessions type, UserEntity user);
    List<TrainingEntity> findTrainingsByUser(UserEntity userE);
    Map<DayOfWeek, List<TrainingEntity>> getAllTrainingsGroupedByDay();
    void removeTrainingFromUser(Long trainingId, UserEntity userEntity);
    void cancelCompletedTraining(Long trainingId, Long userId);
    void completeTraining(Long trainingId, Long userId);
}
