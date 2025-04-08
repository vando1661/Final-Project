package com.example.finalproject.model.entity;

import com.example.finalproject.model.enums.PlanEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "plans")
public class PlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PlanEnum plan;

    private Double price;

    private Integer credits;

    private boolean hatKidsZone;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> users = new ArrayList<>();


    public PlanEntity(PlanEnum plan, Double price, Integer credits, boolean hatKidsZone) {
        this.plan = plan;
        this.price = price;
        this.credits = credits;
        this.hatKidsZone = hatKidsZone;
    }


}
