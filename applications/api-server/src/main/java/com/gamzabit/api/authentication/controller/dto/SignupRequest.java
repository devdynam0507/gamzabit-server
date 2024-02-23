package com.gamzabit.api.authentication.controller.dto;

import lombok.Data;

@Data
public class SignupRequest {

    private final String email, password, nickname;
}
