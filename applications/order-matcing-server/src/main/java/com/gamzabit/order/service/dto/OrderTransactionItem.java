package com.gamzabit.order.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gamzabit.domain.redis.orderbook.dto.OrderTransaction;

public record OrderTransactionItem(
    Long userId,
    Long orderId,
    Long assetId,
    String orderState,
    String orderType,
    BigDecimal concludedAmount,
    Long concludedPrice,
    LocalDateTime concludedTime
) {

    public static OrderTransactionItem from(OrderTransaction transaction) {
        return new OrderTransactionItem(
            transaction.userId(),
            transaction.orderId(),
            transaction.assetId(),
            transaction.orderState(),
            transaction.orderType(),
            transaction.concludedAmount(),
            transaction.concludedPrice(),
            transaction.concludedTime()
        );
    }
}