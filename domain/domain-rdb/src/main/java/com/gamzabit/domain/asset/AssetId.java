package com.gamzabit.domain.asset;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class AssetId {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public AssetId(Long id) {
        this.id = id;
    }

    public Long longValue() {
        return id;
    }
}
