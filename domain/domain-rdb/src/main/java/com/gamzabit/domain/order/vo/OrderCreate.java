package com.gamzabit.domain.order.vo;

import java.math.BigDecimal;

import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.order.OrderEntity;
import com.gamzabit.domain.order.OrderEntity.OrderState;

public record OrderCreate(

    Long symbolId,
    BigDecimal amount,
    Long orderPriceKrw,
    BigDecimal assetBuyPriceKrw,
    OrderEntity.OrderType orderType
) {

    public AssetPrice toAssetPrice() {
        return new AssetPrice(amount);
    }

    public Long toAssetId() {
        return symbolId;
    }

    public OrderEntity toEntity(Long userId) {
        return OrderEntity.builder()
            .orderPrice(orderPriceKrw)
            .assetBuyPrice(assetBuyPriceKrw)
            .orderQuantity(amount)
            .assetId(symbolId)
            .userId(userId)
            .orderState(OrderState.Pending)
            .orderType(orderType)
            .build();
    }
}
