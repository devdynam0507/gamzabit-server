package com.gamzabit.domain.user.vo;

import java.util.List;

import com.gamzabit.domain.asset.AssetPrice;

public record AggregatedUserAsset(
    AssetPrice totalUserAssetKrw,
    List<UserAsset> assets
) {
}
