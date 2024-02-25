package com.gamzabit.api.user.domain;

import java.util.List;

public interface UserAssetQueryRepository {

    List<UserAssetEntity> findAllByUserId(Long userId);
}
