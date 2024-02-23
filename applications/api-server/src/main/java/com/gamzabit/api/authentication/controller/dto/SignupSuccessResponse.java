package com.gamzabit.api.authentication.controller.dto;

import lombok.Data;

@Data
public class SignupSuccessResponse {

    private final String email, nickname;
}
