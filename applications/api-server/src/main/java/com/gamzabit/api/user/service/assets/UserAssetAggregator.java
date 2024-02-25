package com.gamzabit.api.user.service.assets;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gamzabit.api.user.service.assets.vo.AggregatedUserAsset;
import com.gamzabit.api.user.service.assets.vo.UserAsset;
import com.gamzabit.api.user.service.assets.vo.UserAssetWithKrw;
import com.gamzabit.api.user.service.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAssetAggregator {

    private final UserAssetReader userAssetReader;

    public AggregatedUserAsset aggregateAssetsToKrw(User user) {
        List<UserAsset> userAssets = userAssetReader.getUserAssets(user);
        BigInteger totalKrw = userAssets.stream()
            .map(UserAsset::amount)
            // TODO: 가상화폐를 원화 가치로 바꾸는 컨버터 추가 구현이 필요함.
            .map(BigDecimal::toBigInteger)
            .reduce(BigInteger.ZERO, BigInteger::add);

        return new AggregatedUserAsset(totalKrw, userAssets);
    }

    public UserAssetWithKrw aggregateAssetWithKrw(User user, String symbolName) {
        UserAsset userAsset = userAssetReader.getSpecificSymbolUserAsset(user, symbolName);
        BigInteger amount = userAsset.amount().toBigInteger();

        return new UserAssetWithKrw(userAsset, amount);
    }
}
