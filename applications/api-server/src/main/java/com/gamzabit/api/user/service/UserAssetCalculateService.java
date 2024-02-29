package com.gamzabit.api.user.service;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.user.UserAssetCalculator;
import com.gamzabit.domain.user.UserAssetReader;
import com.gamzabit.domain.user.vo.AggregatedUserAsset;
import com.gamzabit.domain.user.vo.User;
import com.gamzabit.domain.user.vo.UserAssetWithKrw;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAssetCalculateService {

    private final UserAssetReader userAssetReader;
    private final UserAssetCalculator userAssetCalculator;

    public AggregatedUserAsset aggregateAssetsToKrw(User user) {
        return userAssetCalculator.getTotalKrwPriceOwnAssets(user);
    }

    public UserAssetWithKrw aggregateAssetWithKrw(User user, String symbolName) {
        return userAssetCalculator.convertToKrwFromAsset(user, symbolName.toUpperCase());
    }
}
