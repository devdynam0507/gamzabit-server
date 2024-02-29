package com.gamzabit.domain.user;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class UserId implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public UserId(Long id) {
        this.id = id;
    }

    public Long longValue() {
        return id;
    }
}
