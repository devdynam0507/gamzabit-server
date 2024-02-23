package com.gamzabit.api.user.service.dto;

import java.time.LocalDateTime;

public record User(
    Long id,
    String email,
    String password,
    String nickname,
    Boolean deleted,
    LocalDateTime deletedAt,
    LocalDateTime createdAt
) {
}
