package com.gamzabit.api.authentication.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamzabit.api.authentication.controller.dto.SignupRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequest signupRequest) {

    }
}
