package com.example.finalproject.model.dto.bainding;

import com.example.finalproject.model.enums.PlanEnum;
import jakarta.validation.constraints.NotNull;

public class PlanBindingModel {

    @NotNull
    private String username;

    @NotNull
    private PlanEnum plan;

    @NotNull
    private Integer credits;

    public PlanBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PlanEnum getPlan() {
        return plan;
    }

    public void setPlan(PlanEnum plan) {
        this.plan = plan;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}
