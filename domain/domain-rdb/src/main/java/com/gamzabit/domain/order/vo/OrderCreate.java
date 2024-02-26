package com.gamzabit.domain.order.vo;

import java.math.BigDecimal;

import com.gamzabit.domain.order.OrderEntity;
import com.gamzabit.domain.order.OrderState;
import com.gamzabit.domain.order.OrderType;

public record OrderCreate(

    Long symbolId,
    BigDecimal amount,
    Long orderPriceKrw,
    OrderType orderType
) {

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
