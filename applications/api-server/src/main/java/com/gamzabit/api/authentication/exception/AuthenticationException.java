package com.gamzabit.api.authentication.exception;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(Throwable throwable) {
        super(throwable);
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
