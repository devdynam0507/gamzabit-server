package com.gamzabit.api.user.service.assets.vo;

import java.math.BigDecimal;

import com.gamzabit.api.asset.service.vo.Assets;
import com.gamzabit.api.user.domain.UserAssetEntity;

public record UserAsset(
    BigDecimal amount,
    Assets assets
) {

    public static UserAsset from(UserAssetEntity userAssetEntity) {
        Assets asset = Assets.from(userAssetEntity.getSymbol());

        return new UserAsset(userAssetEntity.getAmount(), asset);
    }
}
