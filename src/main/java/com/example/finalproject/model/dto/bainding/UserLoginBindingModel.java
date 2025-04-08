package com.example.finalproject.model.dto.bainding;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginBindingModel {
    @Size(min = 5, max = 20)
    private String username;

    @Size(min = 3)
    private String password;

    private String email;

    public UserLoginBindingModel() {
    }

}
