package com.gamzabit.domain.asset.vo;

import com.gamzabit.domain.asset.AssetPrice;

public record Assets(
    AssetPrice currentValue,
    String symbolName,
    String symbolDisplayName,
    Boolean delisted
) {}
