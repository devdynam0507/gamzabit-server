package com.gamzabit.domain.asset;

import java.util.Optional;

import com.gamzabit.domain.asset.exception.AssetException;
import com.gamzabit.domain.asset.exception.AssetNotFoundException;

public class AssetValidator {

    private final AssetRepository assetRepository;

    public AssetValidator(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public void validateAssetExists(AssetId assetId) {
        validate(
            assetRepository.findById(assetId.getId())
                .orElseThrow(() -> new AssetNotFoundException("거래소 자산을 찾을 수 없습니다.", String.valueOf(assetId)))
        );
    }

    public void validate(AssetEntity asset) {
        if (asset.isDelisted()) {
            throw new AssetException("상장 폐지된 종목 입니다.");
        }
    }
}
