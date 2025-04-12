package com.example.finalproject.model.entity;

import com.example.finalproject.model.enums.DayOfWeek;
import com.example.finalproject.model.enums.TypesOfSessions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "training")
public class TrainingEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    @Enumerated(EnumType.STRING)
    private TypesOfSessions trainingType;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
