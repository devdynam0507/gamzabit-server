package com.gamzabit.domain.user;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentials {
    private String email;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }
}
