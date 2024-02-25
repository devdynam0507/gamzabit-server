package com.gamzabit.api.asset.exception;

public class AssetNotFoundException extends AssetException {

    public AssetNotFoundException(String message, String assetNameOrId) {
        super(message, assetNameOrId);
    }
}
