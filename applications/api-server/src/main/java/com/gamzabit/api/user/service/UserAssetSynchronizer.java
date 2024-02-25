package com.gamzabit.api.user.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gamzabit.api.asset.domain.SymbolEntity;
import com.gamzabit.api.asset.domain.SymbolRepository;
import com.gamzabit.api.user.domain.UserAssetEntity;
import com.gamzabit.api.user.domain.UserAssetQueryRepository;
import com.gamzabit.api.user.domain.UserAssetRepository;
import com.gamzabit.api.user.domain.UserEntity;
import com.gamzabit.api.user.domain.UserRepository;
import com.gamzabit.api.user.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAssetSynchronizer {

    private final UserRepository userRepository;
    private final UserAssetRepository userAssetRepository;
    private final UserAssetQueryRepository userAssetQueryRepository;
    private final SymbolRepository symbolRepository;

    public void syncUserAssets(UserEntity user) {
        List<SymbolEntity> symbols = symbolRepository.findAllByDelistedFalse();
        List<UserAssetEntity> userAssets = userAssetQueryRepository.findAllByUserId(user.getId());

        List<SymbolEntity> needUpdateSymbols = symbols.stream()
            .filter(symbolEntity -> userAssets.stream()
                .filter(userAssetEntity -> userAssetEntity.getSymbol().getId().equals(symbolEntity.getId()))
                .findFirst()
                .isEmpty()
            )
            .toList();
        List<UserAssetEntity> needCreateUserAssets = needUpdateSymbols.stream()
            .map(symbolEntity -> UserAssetEntity.create(user, symbolEntity, BigDecimal.ZERO))
            .toList();

        userAssetRepository.saveAll(needCreateUserAssets);
    }

    public void syncUserAssets(Long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.valueOf(userId));
        }

        syncUserAssets(user.get());
    }
}
