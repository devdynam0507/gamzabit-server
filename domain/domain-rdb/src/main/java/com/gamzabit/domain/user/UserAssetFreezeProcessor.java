package com.gamzabit.domain.user;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.order.vo.OrderCreate;
import com.gamzabit.domain.user.vo.User;
import com.gamzabit.domain.user.vo.UserAsset;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAssetFreezeProcessor {

    private final UserFreezeAssetRepository userFreezeAssetRepository;
    private final UserAssetProcessor userAssetProcessor;

    @Transactional
    public void unfreeze(User user, Long orderId, String assetTypeString, AssetPrice assetPrice) {
        userAssetProcessor.depositTo(user, assetPrice, assetTypeString);
        userFreezeAssetRepository.deleteByOrderId(orderId);
    }

    public void doFreeze(
        OrderCreate orderCreate,
        User user,
        Long orderId,
        UserAsset userAsset,
        AssetPrice freezeAmount,
        String assetTypeString
    ) {
        UserFreezeAssetEntity userFreezeAssetEntity = new UserFreezeAssetEntity();
        userFreezeAssetEntity.freeze(
            orderCreate.toAssetPrice(),
            userAsset,
            user,
            orderCreate.toAssetId(),
            orderId
        );
        userAssetProcessor.withdrawTo(
            user,
            freezeAmount,
            assetTypeString
        );

        userFreezeAssetRepository.save(userFreezeAssetEntity);
    }
}
