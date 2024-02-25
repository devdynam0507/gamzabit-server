package com.gamzabit.api.user.service.assets.vo;

import java.math.BigInteger;

public record UserAssetWithKrw(
    UserAsset userAsset,
    BigInteger krw
) {
}
