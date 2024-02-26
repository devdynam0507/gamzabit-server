package com.gamzabit.domain.user.vo;

import java.math.BigDecimal;

public record UserAssetWithKrw(
    UserAsset userAsset,
    BigDecimal krw
) {
}
