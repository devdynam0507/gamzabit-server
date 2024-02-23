package com.gamzabit.api.authentication.controller.dto;

import lombok.Data;

@Data
public class SigninRequest {

    private String email, password;
}
