package com.gamzabit.order.service.dto;

import java.math.BigDecimal;

import com.gamzabit.domain.order.OrderEntity.OrderType;

public record OrderMessage(
    Long orderId,
    BigDecimal amount,
    Long orderPriceKrw,
    BigDecimal assetBuyPriceKrw,
    OrderType orderType,
    Long orderCreationTime
) {}
