package com.gamzabit.domain.redis.orderbook.dto;

import java.math.BigDecimal;

public record OrderTransaction(
    Long userId,
    Long orderId,
    String orderState,
    String orderType,
    BigDecimal concludedAmount,
    Long concludedPrice
) {

    public static OrderTransaction createTransaction(
        OrderBookOrderItem orderItem, BigDecimal amount, Long concludedPrice
    ) {
        return new OrderTransaction(
            orderItem.userId(),
            orderItem.orderId(),
            "Concluded",
            orderItem.orderType(),
            amount,
            concludedPrice
        );
    }

    public static OrderTransaction createTransaction(
        OrderBookCreate orderItem, BigDecimal amount, Long concludedPrice
    ) {
        return new OrderTransaction(
            orderItem.userId(),
            orderItem.orderId(),
            "Concluded",
            orderItem.orderType(),
            amount,
            concludedPrice
        );
    }
}
