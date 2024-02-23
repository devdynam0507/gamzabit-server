package com.gamzabit.api.authentication.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gamzabit.api.authentication.exception.AuthenticationException;
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

    public User signup(String email, String password, String nickname) {
        String encodedPassword = passwordEncoder.encode(password);
        UserCreation userCreation = new UserCreation(email, encodedPassword, nickname);
        try {
            return userService.createUser(userCreation);
        } catch (UserAlreadyExistsException e) {
            throw new AuthenticationException(e);
        }
    }
}
