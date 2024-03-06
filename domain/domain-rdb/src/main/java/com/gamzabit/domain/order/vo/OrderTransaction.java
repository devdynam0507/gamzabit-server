package com.gamzabit.domain.order.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderTransaction(
    Long userId,
    Long orderId,
    Long assetId,
    String orderState,
    String orderType,
    BigDecimal concludedAmount,
    Long concludedPrice,
    LocalDateTime concludedTime
) {}
