package com.gamzabit.api.user.service.assets;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gamzabit.api.asset.domain.SymbolRepository;
import com.gamzabit.api.asset.service.AssetPriceCalculator;
import com.gamzabit.api.user.service.assets.vo.AggregatedUserAsset;
import com.gamzabit.api.user.service.assets.vo.UserAsset;
import com.gamzabit.api.user.service.assets.vo.UserAssetWithKrw;
import com.gamzabit.api.user.service.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAssetAggregator {

    private final UserAssetReader userAssetReader;
    private final AssetPriceCalculator assetPriceCalculator;

    public AggregatedUserAsset aggregateAssetsToKrw(User user) {
        List<UserAsset> userAssets = userAssetReader.getUserAssets(user);
        BigDecimal totalKrw = userAssets.stream()
            .map(userAsset -> assetPriceCalculator.toKrw(userAsset.assets(), userAsset.amount()))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new AggregatedUserAsset(totalKrw, userAssets);
    }

    public UserAssetWithKrw aggregateAssetWithKrw(User user, String symbolName) {
        UserAsset userAsset = userAssetReader.getSpecificSymbolUserAsset(user, symbolName);
        BigDecimal amount = assetPriceCalculator.toKrw(userAsset.assets(), userAsset.amount());

        return new UserAssetWithKrw(userAsset, amount);
    }
}
