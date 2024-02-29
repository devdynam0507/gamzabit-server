package com.gamzabit.domain.user.vo;

import com.gamzabit.domain.asset.AssetPrice;

public record UserAssetWithKrw(
    UserAsset userAsset,
    AssetPrice krw
) {
}
