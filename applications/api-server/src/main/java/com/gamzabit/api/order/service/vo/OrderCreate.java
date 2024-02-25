package com.gamzabit.api.order.service.vo;

import java.math.BigDecimal;

import com.gamzabit.api.order.domain.OrderEntity;
import com.gamzabit.api.order.domain.types.OrderState;
import com.gamzabit.api.order.domain.types.OrderType;
import com.gamzabit.api.user.service.vo.User;

public record OrderCreate(

    Long symbolId,
    BigDecimal amount,
    Long orderPriceKrw,
    OrderType orderType
) {

    public OrderEntity toEntity(User user) {
        return OrderEntity.builder()
            .orderPrice(orderPriceKrw)
            .orderQuantity(amount)
            .assetId(symbolId)
            .userId(user.id())
            .orderState(OrderState.Pending)
            .orderType(orderType)
            .build();
    }
}
