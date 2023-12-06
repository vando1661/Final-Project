package com.example.finalproject.model.entity;

import com.example.finalproject.model.enums.PlanEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "plans")
public class PlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PlanEnum plan;

    private Integer credits;

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

    public Integer getCredit() {
        return credits;
    }

    public void setCredit(Integer credits) {
        this.credits = credits;
    }
}
