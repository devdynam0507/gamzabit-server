package com.gamzabit.order.core;

import java.math.BigDecimal;

import com.gamzabit.domain.redis.orderbook.dto.OrderBookCreate;

public record OrderEngineIncomingMessage(
    Long symbolId,
    Long userId,
    Long orderId,
    BigDecimal amount,
    Long orderPriceKrw,
    BigDecimal assetBuyPriceKrw,
    String orderType,
    Long orderCreationTime
) {

    public OrderBookCreate toOrderBookCreationDto() {
        return new OrderBookCreate(
            symbolId,
            userId,
            orderId,
            amount,
            orderPriceKrw,
            assetBuyPriceKrw,
            orderType,
            orderCreationTime
        );
    }
}
