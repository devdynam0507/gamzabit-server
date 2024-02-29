package com.gamzabit.domain.user;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;

@Embeddable
@Getter
public class UserFreezeAssetId implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
