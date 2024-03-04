package com.gamzabit.domain.redis.orderbook.dto;

import java.math.BigDecimal;

import com.gamzabit.domain.redis.orderbook.OrderBook;

public record OrderBookCreate(
    Long id,
    Long userId,
    Long orderId,
    BigDecimal amount,
    Long orderPriceKrw,
    BigDecimal assetBuyPrice,
    String orderType,
    Long orderCreationTime
) {

    public OrderBookCreate branchNewQuantity(BigDecimal newQuantity) {
        return new OrderBookCreate(
            id,
            userId,
            orderId,
            newQuantity,
            orderPriceKrw,
            assetBuyPrice,
            orderType,
            orderCreationTime
        );
    }

    public OrderBookOrderItem toOrderBookDto() {
        return new OrderBookOrderItem(
            id,
            userId,
            orderId,
            amount,
            orderPriceKrw,
            assetBuyPrice,
            orderType,
            orderCreationTime
        );
    }

    public OrderBook toOrderBook() {
        return new OrderBook(
            id,
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
