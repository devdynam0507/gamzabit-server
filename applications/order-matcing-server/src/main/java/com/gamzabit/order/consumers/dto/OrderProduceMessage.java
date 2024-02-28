package com.gamzabit.order.consumers.dto;

import java.math.BigDecimal;

import com.gamzabit.domain.order.OrderType;
import com.gamzabit.domain.order.vo.OrderCreate;
import com.gamzabit.order.service.dto.Order;

public record OrderProduceMessage(
    Long symbolId,
    Long userId,
    Long orderId,
    BigDecimal amount,
    Long orderPriceKrw,
    OrderType orderType,
    Long orderCreationTime
) {

    public Order toOrder() {
        return new Order(orderId, amount, orderPriceKrw, orderType, orderCreationTime);
    }

    public static OrderProduceMessage from(Long userId, OrderCreate orderCreate, Long orderId, Long orderCreationTime) {
        return new OrderProduceMessage(
            orderCreate.symbolId(),
            userId,
            orderId,
            orderCreate.amount(),
            orderCreate.orderPriceKrw(),
            orderCreate.orderType(),
            orderCreationTime
        );
    }
}
