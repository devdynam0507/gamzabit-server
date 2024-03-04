package com.gamzabit.domain.order.vo;

import java.time.LocalDateTime;

import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.order.OrderEntity.OrderState;
import com.gamzabit.domain.order.OrderEntity.OrderType;
import com.gamzabit.domain.user.AssetAmount;

public record Order(
    Long id,
    Long userId,
    Long assetId,
    AssetAmount orderQuantity,
    AssetPrice orderPrice,
    AssetPrice assetPrice,
    OrderType orderType,
    OrderState orderState,
    LocalDateTime orderDate
) {}
