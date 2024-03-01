package com.gamzabit.domain.asset;

public enum DefaultAssetTypes {
    KRW(1L), BTC(2L);

    private final Long id;

    DefaultAssetTypes(Long id) {
        this.id = id;
    }

    public Long id() {
        return id;
    }
}
