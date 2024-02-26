package com.gamzabit.domain.user.exception;

public class UserNotFoundException extends UserException {

    public UserNotFoundException(String emailOrNickname) {
        super(emailOrNickname + " 유저는 존재하지 않습니다.", emailOrNickname);
    }
}
