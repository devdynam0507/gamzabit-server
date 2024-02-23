package com.gamzabit.api.authentication.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gamzabit.api.authentication.controller.dto.SigninResponse;
import com.gamzabit.api.authentication.exception.AuthenticationException;
import com.gamzabit.api.infrastructure.security.jwt.JwtProvider;
import com.gamzabit.api.infrastructure.security.jwt.JwtStrategy;
import com.gamzabit.api.infrastructure.security.jwt.JwtTokenType;
import com.gamzabit.api.user.exception.UserAlreadyExistsException;
import com.gamzabit.api.user.service.UserService;
import com.gamzabit.api.user.service.dto.UserCreation;
import com.gamzabit.api.user.service.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtStrategy jwtStrategy;
    private static final long accessTokenExpired = 3600 * 24 * 1000;
    private static final long refreshTokenExpired = (3600 * 24 * 1000) * 7;

    public User signup(String email, String password, String nickname) {
        String encodedPassword = passwordEncoder.encode(password);
        UserCreation userCreation = new UserCreation(email, encodedPassword, nickname);
        try {
            return userService.createUser(userCreation);
        } catch (UserAlreadyExistsException e) {
            throw new AuthenticationException(e);
        }
    }

    public SigninResponse signin(String email, String password) {
        try {
            User user = userService.findUserByEmail(email);
            boolean isPasswordMatched = passwordEncoder.matches(password, user.password());
            if (!isPasswordMatched) {
                throw new AuthenticationException("비밀번호가 일치하지 않습니다.");
            }
            JwtProvider accessTokenProvider = jwtStrategy.getProvider(JwtTokenType.Access);
            String accessToken = accessTokenProvider.encrypt("id", user.id(), accessTokenExpired);

            return new SigninResponse(email, accessToken);
        } catch (Throwable e) {
            throw new AuthenticationException(e);
        }
    }
}
