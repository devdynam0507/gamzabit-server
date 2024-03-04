package com.gamzabit.domain.redis.orderbook.dto;

import java.util.ArrayList;
import java.util.List;

public record OrderResults(
   List<OrderBookOrderItem> orderBranches,
   List<OrderTransaction> concludedOrders
) {

    public boolean isEmpty() {
        return orderBranches.isEmpty();
    }

    public static OrderResults empty() {
        return new OrderResults(new ArrayList<>(), new ArrayList<>());
    }
}
