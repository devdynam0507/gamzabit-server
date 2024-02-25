package com.gamzabit.api.infrastructure.security.dto;

import com.gamzabit.api.user.service.vo.User;

import lombok.Data;

@Data
public class AuthenticatedUser {

    private final User user;

    public boolean isAuthenticated() {
        return user != null;
    }
}
