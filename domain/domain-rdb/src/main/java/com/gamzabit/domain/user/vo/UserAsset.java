package com.gamzabit.domain.user.vo;

import java.math.BigDecimal;

import com.gamzabit.domain.asset.vo.Assets;
import com.gamzabit.domain.user.UserAssetEntity;

public record UserAsset(
    BigDecimal amount,
    Assets assets
) {

    public static UserAsset from(UserAssetEntity userAssetEntity) {
        Assets asset = Assets.from(userAssetEntity.getSymbol());

        return new UserAsset(userAssetEntity.getAmount(), asset);
    }
}
