package com.gamzabit.api.user.domain;

import java.util.List;
import java.util.Optional;

public interface UserAssetQueryRepository {

    List<UserAssetEntity> findAllByUserId(Long userId);

    Optional<UserAssetEntity> findByUserIdAndSymbolName(Long userId, String symbolName);
}
