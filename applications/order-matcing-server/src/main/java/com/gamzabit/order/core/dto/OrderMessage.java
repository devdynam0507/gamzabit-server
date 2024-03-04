package com.gamzabit.order.core.dto;

import java.math.BigDecimal;

import com.gamzabit.domain.order.OrderEntity.OrderType;

public record OrderMessage(
    Long orderId,
    BigDecimal amount,
    Long orderPriceKrw,
    OrderType orderType,
    Long orderCreationTime
) {}
