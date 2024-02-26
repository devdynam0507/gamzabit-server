package com.gamzabit.domain.user.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private final String email;

    public UserException(String message, String email) {
        super(message);
        this.email = email;
    }

    public UserException(String email) {
        this("유저 관련 에러가 발생하였습니다.", email);
    }
}
