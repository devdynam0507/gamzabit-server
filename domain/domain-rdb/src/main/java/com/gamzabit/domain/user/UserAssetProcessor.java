package com.gamzabit.domain.user;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.asset.exception.AssetNotFoundException;
import com.gamzabit.domain.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAssetProcessor {

    private final UserAssetQueryRepository userAssetQueryRepository;

    public void depositTo(User user, AssetPrice assetPrice, String assetTypeString) {
        UserAssetEntity userAssetEntity =
            userAssetQueryRepository.findByUserIdAndAssetName(user.id(), assetTypeString)
                .orElseThrow(() ->
                    new AssetNotFoundException("유저의 '" + assetTypeString + "' 자산이 존재하지 않습니다.", assetTypeString)
                );

        userAssetEntity.deposit(assetPrice);
    }

    public void withdrawTo(User user, AssetPrice withdrawAmount, String assetTypeString) {
        UserAssetEntity userAssetEntity =
            userAssetQueryRepository.findByUserIdAndAssetName(user.id(), assetTypeString)
                .orElseThrow(() ->
                    new AssetNotFoundException("유저의 '" + assetTypeString + "' 자산이 존재하지 않습니다.", assetTypeString)
                );

        userAssetEntity.withdraw(withdrawAmount);
    }
}
