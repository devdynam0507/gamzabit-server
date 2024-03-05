package com.gamzabit.order.consumers.dto;

import com.gamzabit.domain.order.OrderEntity.OrderType;

public record OrderCancelMessage(
    Long assetId,
    Long userId,
    Long orderId,
    OrderType previousOrderType
) {

}
