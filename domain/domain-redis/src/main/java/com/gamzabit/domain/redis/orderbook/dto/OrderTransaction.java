package com.gamzabit.domain.redis.orderbook.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderTransaction(
    Long userId,
    Long orderId,
    Long assetId,
    String orderState,
    String orderType,
    BigDecimal concludedAmount,
    Long concludedPrice,
    LocalDateTime concludedTime
) {

    public static OrderTransaction createTransaction(
        OrderBookOrderItem orderItem, BigDecimal amount, Long concludedPrice
    ) {
        return new OrderTransaction(
            orderItem.userId(),
            orderItem.orderId(),
            orderItem.assetId(),
            "Concluded",
            orderItem.orderType(),
            amount,
            concludedPrice,
            LocalDateTime.now()
        );
    }

    public static OrderTransaction createTransaction(
        OrderBookCreate orderItem, BigDecimal amount, Long concludedPrice
    ) {
        return new OrderTransaction(
            orderItem.userId(),
            orderItem.orderId(),
            orderItem.id(),
            "Concluded",
            orderItem.orderType(),
            amount,
            concludedPrice,
            LocalDateTime.now()
        );
    }
}
