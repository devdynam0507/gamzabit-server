package com.gamzabit.order.service.dto;

import java.math.BigDecimal;

import com.gamzabit.domain.order.OrderType;

public record Order(
    Long orderId,
    BigDecimal amount,
    Long orderPriceKrw,
    OrderType orderType,
    Long orderCreationTime
) {}
