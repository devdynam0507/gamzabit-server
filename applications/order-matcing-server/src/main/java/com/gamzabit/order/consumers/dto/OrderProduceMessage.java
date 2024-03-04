package com.gamzabit.order.consumers.dto;

import java.math.BigDecimal;

import com.gamzabit.domain.order.OrderEntity.OrderType;
import com.gamzabit.domain.order.vo.OrderCreate;
import com.gamzabit.domain.redis.orderbook.dto.OrderBookCreate;
import com.gamzabit.order.service.dto.OrderMessage;

public record OrderProduceMessage(
    Long symbolId,
    Long userId,
    Long orderId,
    BigDecimal amount,
    Long orderPriceKrw,
    BigDecimal assetBuyPriceKrw,
    OrderType orderType,
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
            orderType.name(),
            orderCreationTime
        );
    }

    public OrderMessage toOrder() {
        return new OrderMessage(orderId, amount, orderPriceKrw, assetBuyPriceKrw, orderType, orderCreationTime);
    }

    public static OrderProduceMessage from(Long userId, OrderCreate orderCreate, Long orderId, Long orderCreationTime) {
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
