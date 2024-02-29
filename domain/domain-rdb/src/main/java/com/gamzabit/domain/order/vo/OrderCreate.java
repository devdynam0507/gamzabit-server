package com.gamzabit.domain.order.vo;

import java.math.BigDecimal;

import com.gamzabit.domain.asset.AssetId;
import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.order.OrderEntity;
import com.gamzabit.domain.order.OrderEntity.OrderState;

public record OrderCreate(

    Long symbolId,
    BigDecimal amount,
    Long orderPriceKrw,
    OrderEntity.OrderType orderType
) {

    public AssetPrice toAssetPrice() {
        return new AssetPrice(amount);
    }

    public AssetId toAssetId() {
        return new AssetId(symbolId);
    }

    public OrderEntity toEntity(Long userId) {
        return OrderEntity.builder()
            .orderPrice(orderPriceKrw)
            .orderQuantity(amount)
            .assetId(symbolId)
            .userId(userId)
            .orderState(OrderState.Pending)
            .orderType(orderType)
            .build();
    }
}
