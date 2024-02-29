package com.gamzabit.domain.order.vo;

import java.time.LocalDateTime;

import com.gamzabit.domain.asset.AssetId;
import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.order.OrderEntity.OrderState;
import com.gamzabit.domain.order.OrderEntity.OrderType;
import com.gamzabit.domain.order.OrderId;
import com.gamzabit.domain.user.AssetAmount;
import com.gamzabit.domain.user.UserId;

public record Order(
    OrderId id,
    UserId userId,
    AssetId assetId,
    AssetAmount orderQuantity,
    AssetPrice orderPrice,
    OrderType orderType,
    OrderState orderState,
    LocalDateTime orderDate
) {}
