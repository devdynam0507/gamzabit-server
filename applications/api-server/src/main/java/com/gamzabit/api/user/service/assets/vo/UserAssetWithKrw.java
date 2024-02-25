package com.gamzabit.api.user.service.assets.vo;

import java.math.BigDecimal;

public record UserAssetWithKrw(
    UserAsset userAsset,
    BigDecimal krw
) {
}
