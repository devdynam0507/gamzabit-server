package com.gamzabit.domain.asset;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.asset.exception.AssetNotFoundException;
import com.gamzabit.domain.asset.vo.Assets;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SymbolReader {

    private final SymbolRepository symbolRepository;

    public Assets getSymbolById(Long id) {
        SymbolEntity symbol = symbolRepository.findById(id)
            .orElseThrow(() -> new AssetNotFoundException(id + "를 찾을 수 없습니다.", String.valueOf(id)));

        return Assets.from(symbol);
    }
}
