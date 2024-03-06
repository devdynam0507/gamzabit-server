package com.gamzabit.order.service.dto;

import java.util.List;

public record OrderTransactionMessage(
    List<OrderTransactionItem> transactions
) {
}
