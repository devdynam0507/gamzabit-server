package com.gamzabit.api.authentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gamzabit.api.authentication.exception.AuthenticationException;
import com.gamzabit.api.infrastructure.common.Responses;
import com.gamzabit.api.user.exception.UserAlreadyExistsException;
import com.gamzabit.api.user.exception.UserNotFoundException;

@RestControllerAdvice
public class AuthenticationErrorAdvice {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Responses<String> authenticationExceptionHandler(AuthenticationException e) {
        Class<?> nestedThrowableClass = e.getCause().getClass();
        if (nestedThrowableClass.isAssignableFrom(UserAlreadyExistsException.class)) {
            return Responses.error(null, HttpStatus.FORBIDDEN, "이미 유저가 존재합니다.");
        }
        if (nestedThrowableClass.isAssignableFrom(UserNotFoundException.class)) {
            return Responses.error(null, HttpStatus.FORBIDDEN, "유저가 존재하지 않습니다.");
        }
        return Responses.error(null, HttpStatus.FORBIDDEN, "인증 오류");
    }
}
