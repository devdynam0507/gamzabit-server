package com.gamzabit.domain.user.vo;

import java.time.LocalDateTime;

import com.gamzabit.domain.user.UserCredentials;

public record User(
    Long id,
    UserCredentials userCredentials,
    String nickname,
    Boolean deleted,
    LocalDateTime deletedAt,
    LocalDateTime createdAt
) {

    public static User createOnlyId(Long userId) {
        return new User(userId, null, null, null, null, null);
    }
}
