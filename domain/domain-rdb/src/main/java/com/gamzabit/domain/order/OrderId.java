package com.gamzabit.domain.order;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class OrderId implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long longValue() {
        return id;
    }
}
