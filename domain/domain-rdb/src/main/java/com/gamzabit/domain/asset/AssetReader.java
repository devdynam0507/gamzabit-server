package com.gamzabit.domain.asset;

import com.gamzabit.domain.asset.exception.AssetNotFoundException;
import com.gamzabit.domain.asset.vo.Assets;

public class AssetReader {

    private final AssetRepository assetRepository;

    public AssetReader(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public Assets getSymbolById(Long id) {
        AssetEntity asset = assetRepository.findById(id)
            .orElseThrow(() -> new AssetNotFoundException(id + "를 찾을 수 없습니다.", String.valueOf(id)));

        return asset.toAssets();
    }
}
