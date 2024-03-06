package com.gamzabit.domain.redis.orderbook.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderOperationBoard(
    BigDecimal price,
    // 매수
    List<OrderOperationBoardItem> bids,
    // 매도
    List<OrderOperationBoardItem> asks
) {

    public static OrderOperationBoardItem create(BigDecimal price, BigDecimal amount) {
        return new OrderOperationBoardItem(price, amount);
    }

    public record OrderOperationBoardItem(
        BigDecimal price,
        BigDecimal amount
    ) {}
}
