package com.gamzabit.domain.asset;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.asset.exception.AssetException;
import com.gamzabit.domain.asset.exception.AssetNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssetValidator {

    private final AssetRepository assetRepository;

    public void validateAssetExists(Long assetId) {
        validate(
            assetRepository.findById(assetId)
                .orElseThrow(() -> new AssetNotFoundException("거래소 자산을 찾을 수 없습니다.", String.valueOf(assetId)))
        );
    }

    public void validate(AssetEntity asset) {
        if (asset.isDelisted()) {
            throw new AssetException("상장 폐지된 종목 입니다.");
        }
    }
}
