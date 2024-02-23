package com.gamzabit.api.authentication.controller.dto;

import lombok.Data;

@Data
public class SigninResponse {

    private final String email, accessToken;
}
