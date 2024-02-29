package com.gamzabit.order.service.dto;

import java.math.BigDecimal;

public record Order(
    Long orderId,
    BigDecimal amount,
    Long orderPriceKrw,
    OrderType orderType,
    Long orderCreationTime
) {}
