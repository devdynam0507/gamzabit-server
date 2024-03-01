package com.gamzabit.domain.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.asset.AssetEntity;
import com.gamzabit.domain.asset.AssetRepository;
import com.gamzabit.domain.user.exception.UserNotFoundException;
import com.gamzabit.domain.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAssetInitializer {

    private final UserRepository userRepository;
    private final UserAssetRepository userAssetRepository;
    private final AssetRepository assetRepository;

    public void initializeDefaultAsset(User user) {
        List<AssetEntity> exchangeAssets = assetRepository.findAll();
        UserEntity userEntity = userRepository.findById(user.id())
            .orElseThrow(() -> new UserNotFoundException(user.nickname()));
        List<UserAssetEntity> userAssetEntities = userAssetRepository.findAll();
        List<Long> userAssetIds = userAssetEntities.stream()
            .map(UserAssetEntity::getId)
            .toList();
        List<UserAssetEntity> filteredAssets = exchangeAssets.stream()
            .filter(asset -> !userAssetIds.contains(asset.getId()))
            .map(userEntity::createUserAsset)
            .toList();

        userAssetRepository.saveAll(filteredAssets);
    }
}
