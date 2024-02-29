package com.gamzabit.domain.user.vo;

import java.time.LocalDateTime;

import com.gamzabit.domain.user.UserId;

public record User(
    UserId id,
    String email,
    String password,
    String nickname,
    Boolean deleted,
    LocalDateTime deletedAt,
    LocalDateTime createdAt
) {}
