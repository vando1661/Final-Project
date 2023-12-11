package com.example.finalproject.model.entity;

import com.example.finalproject.model.enums.PlanEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "plans")
public class PlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Max(3)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PlanEnum plan;

    private Double price;

    private Integer credits;

    private boolean hatKidsZone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanEnum getPlan() {
        return plan;
    }

    public void setPlan(PlanEnum plan) {
        this.plan = plan;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public boolean isHatKidsZone() {
        return hatKidsZone;
    }

    public void setHatKidsZone(boolean hatKidsZone) {
        this.hatKidsZone = hatKidsZone;
    }
}
