package com.gamzabit.api.user.exception;

public class UserAlreadyExistsException extends UserException {

    public UserAlreadyExistsException(String email) {
        super("유저가 이미 존재합니다.",  email);
    }
}
