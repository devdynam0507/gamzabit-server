package com.gamzabit.domain.order.vo;

import java.time.LocalDateTime;

import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.user.AssetAmount;

public record OrderHistory(
    Order order,
    LocalDateTime txDate,
    AssetAmount concludedQuantity,
    AssetPrice concludedKrw
) {}
