package com.gamzabit.api.authentication.controller.dto;

import lombok.Data;

@Data
public class SignupRequest {

    private String email, password, nickname;
}
