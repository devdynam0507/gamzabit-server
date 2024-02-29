package com.gamzabit.domain.user;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.order.OrderId;
import com.gamzabit.domain.order.vo.OrderCreate;
import com.gamzabit.domain.user.vo.User;
import com.gamzabit.domain.user.vo.UserAsset;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAssetFreezeProcessor {

    private final UserFreezeAssetRepository userFreezeAssetRepository;
    private final UserAssetProcessor userAssetProcessor;

    public void unfreeze(User user, OrderId orderId, String assetTypeString, AssetPrice assetPrice) {
        userAssetProcessor.depositTo(user, assetPrice, assetTypeString);
        userFreezeAssetRepository.deleteByOrderId(orderId.longValue());
    }

    public void doFreeze(
        OrderCreate orderCreate,
        User user,
        OrderId orderId,
        UserAsset userAsset,
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
        userAssetProcessor.withdrawTo(user, orderCreate.toAssetPrice(), assetTypeString);

        userFreezeAssetRepository.save(userFreezeAssetEntity);
    }
}
