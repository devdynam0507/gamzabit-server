package com.gamzabit.domain.asset;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.asset.exception.AssetNotFoundException;
import com.gamzabit.domain.asset.vo.Assets;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssetReader {

    private final AssetRepository assetRepository;

    public Assets getSymbolById(Long id) {
        AssetEntity asset = assetRepository.findById(id)
            .orElseThrow(() -> new AssetNotFoundException(id + "를 찾을 수 없습니다.", String.valueOf(id)));

        return asset.toAssets();
    }
}
