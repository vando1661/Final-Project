package com.example.finalproject.model.dto.bainding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterBindingModel {
    @Size(min = 5, max = 20)
    private String username;

    @Size(min = 2, max = 20)
    private String name;

    @Size(min = 3, max = 20)
    private String surname;

    @Email
    private String email;

    @Size(min = 3)
    private String password;

    @Size(min = 3)
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

}
