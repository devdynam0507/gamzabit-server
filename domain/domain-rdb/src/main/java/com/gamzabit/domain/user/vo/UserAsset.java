package com.gamzabit.domain.user.vo;

import com.gamzabit.domain.asset.vo.Assets;
import com.gamzabit.domain.user.AssetAmount;

public record UserAsset(
    AssetAmount amount,
    Assets assets
) {}
