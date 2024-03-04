package com.gamzabit.domain.redis.orderbook.dto;

import java.math.BigDecimal;

public record OrderBookOrderItem(
    Long assetId,
    Long userId,
    Long orderId,
    BigDecimal amount,
    Long orderPriceKrw,
    BigDecimal assetBuyPrice,
    String orderType,
    Long orderCreationTime
) {

    public OrderBookCreate toOrderBookCreationDto() {
        return new OrderBookCreate(
            assetId,
            userId,
            orderId,
            amount,
            orderPriceKrw,
            assetBuyPrice,
            orderType,
            orderCreationTime
        );
    }
}
