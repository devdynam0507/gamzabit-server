package com.gamzabit.api.order.service.dto;

import java.math.BigDecimal;

import com.gamzabit.domain.order.OrderType;
import com.gamzabit.domain.order.vo.OrderCreate;

public record OrderProduceMessage(
    Long symbolId,
    Long userId,
    Long orderId,
    BigDecimal amount,
    Long orderPriceKrw,
    OrderType orderType
) {

    public static OrderProduceMessage from(Long userId, OrderCreate orderCreate, Long orderId) {
        return new OrderProduceMessage(
            orderCreate.symbolId(),
            userId,
            orderId,
            orderCreate.amount(),
            orderCreate.orderPriceKrw(),
            orderCreate.orderType()
        );
    }
}
