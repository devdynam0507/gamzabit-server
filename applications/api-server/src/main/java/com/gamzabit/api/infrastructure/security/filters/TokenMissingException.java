package com.gamzabit.api.infrastructure.security.filters;

public class TokenMissingException extends RuntimeException {

    public TokenMissingException(String message) {
        super(message);
    }

    public TokenMissingException(Throwable throwable) {
        super(throwable);
    }
}
