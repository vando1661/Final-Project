package com.example.finalproject.service.impl;

import com.example.finalproject.model.entity.TrainingEntity;
import com.example.finalproject.model.entity.UserEntity;
import com.example.finalproject.model.enums.DayOfWeek;
import com.example.finalproject.model.enums.TypesOfSessions;
import com.example.finalproject.repository.TrainingRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.TrainingService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;

    public TrainingServiceImpl(UserRepository userRepository, TrainingRepository trainingRepository) {
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
    }

    @Override
    public void assignTrainingToUser(DayOfWeek day, TypesOfSessions type, UserEntity user) {
        if (user.getPlan() == null || user.getPlan().getCredits() <= 0) {
            throw new IllegalStateException("Not enough credits or no active plan");
        }


        boolean alreadyExists = user.getTrainings().stream()
                .anyMatch(t -> t.getDay() == day && t.getTrainingType() == type);

        if (alreadyExists) {
            throw new RuntimeException("You already have this training in your schedule");
        }

        TrainingEntity training = new TrainingEntity();
        training.setDay(day);
        training.setTrainingType(type);
        training.setUser(user);
        training.setStatus(false);

        trainingRepository.save(training);


        user.getPlan().setCredits(user.getPlan().getCredits() - 1);
        userRepository.save(user);
    }

    @Override
    public List<TrainingEntity> findTrainingsByUser(UserEntity user) {
        return trainingRepository.findByUser(user);
    }

    @Override
    public Map<DayOfWeek, List<TrainingEntity>> getAllTrainingsGroupedByDay() {
        return Arrays.stream(DayOfWeek.values())
                .collect(Collectors.toMap(
                        day -> day,
                        day -> Arrays.stream(TypesOfSessions.values())
                                .map(type -> {

                                    TrainingEntity training = new TrainingEntity();
                                    training.setDay(day);
                                    training.setTrainingType(type);
                                    return training;
                                })
                                .collect(Collectors.toList())
                ));
    }

    @Override
    public void removeTrainingFromUser(Long trainingId, UserEntity user) {
        TrainingEntity training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new RuntimeException("Training not found"));

        if (!training.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You can only remove your own trainings");
        }
        if (!training.isStatus() && user.getPlan() != null) {
            user.getPlan().setCredits(user.getPlan().getCredits() + 1);
            userRepository.save(user);
        }


        trainingRepository.delete(training);
    }

    @Transactional
    @Override
    public void completeTraining(Long trainingId, Long userId) {
        TrainingEntity training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new EntityNotFoundException("Training not found"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!training.getUser().getId().equals(userId)) {
            throw new RuntimeException("This is not your training");
        }

        if (training.isStatus()) {
            throw new RuntimeException("Training already completed");
        }

        training.setStatus(true);
        trainingRepository.save(training);
    }

    @Transactional
    @Override
    public void cancelCompletedTraining(Long trainingId, Long userId) {
        TrainingEntity training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new EntityNotFoundException("Training not found"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!training.getUser().getId().equals(userId)) {
            throw new RuntimeException("This is not your training");
        }

        if (!training.isStatus()) {
            throw new RuntimeException("Training is not completed");
        }

        training.setStatus(false);
        trainingRepository.save(training);

        if (user.getPlan() != null) {
            user.getPlan().setCredits(user.getPlan().getCredits() + 1);
            userRepository.save(user);
        }
    }
}