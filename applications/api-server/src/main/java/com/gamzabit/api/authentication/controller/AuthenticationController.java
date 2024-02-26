package com.gamzabit.api.authentication.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamzabit.api.authentication.controller.dto.SigninRequest;
import com.gamzabit.api.authentication.controller.dto.SigninResponse;
import com.gamzabit.api.authentication.controller.dto.SignupRequest;
import com.gamzabit.api.authentication.controller.dto.SignupSuccessResponse;
import com.gamzabit.api.authentication.service.AuthenticationService;
import com.gamzabit.api.infrastructure.common.Responses;
import com.gamzabit.domain.user.vo.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public Responses<SignupSuccessResponse> signup(@RequestBody SignupRequest signupRequest) {
        User user = authenticationService.signup(
            signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getNickname()
        );
        SignupSuccessResponse response = new SignupSuccessResponse(user.email(), user.nickname());

        return Responses.ok("회원가입을 성공적으로 완료하였습니다.", response);
    }

    @PostMapping("/signin")
    public Responses<SigninResponse> signin(@RequestBody SigninRequest signinRequest) {
        SigninResponse response = authenticationService.signin(signinRequest.getEmail(), signinRequest.getPassword());

        return Responses.ok("로그인을 성공적으로 완료하였습니다.", response);
    }
}
