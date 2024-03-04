package com.gamzabit.api.order.service.dto;

import java.math.BigDecimal;

import com.gamzabit.domain.order.OrderEntity;
import com.gamzabit.domain.order.vo.OrderCreate;

public record OrderProduceMessage(
    Long symbolId,
    Long userId,
    Long orderId,
    BigDecimal amount,
    Long orderPriceKrw,
    BigDecimal assetBuyPriceKrw,
    OrderEntity.OrderType orderType,
    Long orderCreationTime
) {

    public static OrderProduceMessage from(
        Long userId,
        OrderCreate orderCreate,
        Long orderId,
        Long orderCreationTime
    ) {
        return new OrderProduceMessage(
            orderCreate.symbolId(),
            userId,
            orderId,
            orderCreate.amount(),
            orderCreate.orderPriceKrw(),
            orderCreate.assetBuyPriceKrw(),
            orderCreate.orderType(),
            orderCreationTime
        );
    }
}
