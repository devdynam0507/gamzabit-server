package com.gamzabit.api.order.consumer.dto;

import java.util.List;

public record OrderTransactionMessage(
    List<OrderTransactionItem> transactions
) {
}
