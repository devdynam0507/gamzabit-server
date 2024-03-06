package com.gamzabit.order.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderTransactionItem(
    Long userId,
    Long orderId,
    Long assetId,
    String orderState,
    String orderType,
    BigDecimal concludedAmount,
    Long concludedPrice,
    LocalDateTime concludedTime
) {}