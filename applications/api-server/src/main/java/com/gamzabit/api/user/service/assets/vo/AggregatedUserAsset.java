package com.gamzabit.api.user.service.assets.vo;

import java.math.BigDecimal;
import java.util.List;

public record AggregatedUserAsset(
    BigDecimal totalUserAssetKrw,
    List<UserAsset> assets
) {
}
