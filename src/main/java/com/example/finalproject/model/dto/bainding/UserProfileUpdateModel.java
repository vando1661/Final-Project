package com.example.finalproject.model.dto.bainding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
@NoArgsConstructor
@AllArgsConstructor
@Service
@Getter
public class UserProfileUpdateModel {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String confirmPassword;
}
