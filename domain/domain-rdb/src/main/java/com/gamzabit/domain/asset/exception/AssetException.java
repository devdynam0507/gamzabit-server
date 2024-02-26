package com.gamzabit.domain.asset.exception;

import lombok.Getter;

@Getter
public class AssetException extends RuntimeException {

    private final String assetNameOrId;

    public AssetException(String message, String assetNameOrId) {
        super(message);
        this.assetNameOrId = assetNameOrId;
    }

    public AssetException(String assetNameOrId) {
        this("asset error", assetNameOrId);
    }
}
