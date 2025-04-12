package com.example.finalproject.model.serviceModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserServiceModel {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;

    public UserServiceModel() {
    }

}
