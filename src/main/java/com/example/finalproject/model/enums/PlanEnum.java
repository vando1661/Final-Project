package com.example.finalproject.model.enums;

public enum PlanEnum {
    BASIC ("BASIC"),
    STANDARD("STANDARD"),
    PREMIUM("PREMIUM");

    private final String value;

    PlanEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
