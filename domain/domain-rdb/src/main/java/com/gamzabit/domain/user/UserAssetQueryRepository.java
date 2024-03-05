package com.gamzabit.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserAssetQueryRepository {

    List<UserAssetEntity> findAllByUserId(Long userId);

    Optional<UserAssetEntity> findByUserIdAndAssetName(Long userId, String assetName);

    Optional<UserAssetEntity> findByUserIdAndAssetId(Long userId, Long assetId);
}
