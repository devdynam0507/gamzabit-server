package com.gamzabit.api.user.service.assets.vo;

import java.math.BigInteger;
import java.util.List;

public record AggregatedUserAsset(
    BigInteger totalUserAssetKrw,
    List<UserAsset> assets
) {
}
