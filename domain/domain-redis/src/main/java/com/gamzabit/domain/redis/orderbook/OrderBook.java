package com.gamzabit.domain.redis.orderbook;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.gamzabit.domain.redis.orderbook.dto.OrderBookOrderItem;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@RedisHash("order_book")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderBook implements Serializable {

    @Id
    private Long id;
    private Long userId;
    private Long orderId;
    private BigDecimal amount;
    @Indexed
    private Long orderPriceKrw;
    @Indexed
    private BigDecimal assetBuyPrice;
    private String orderType;
    private Long orderCreationTime;

    @Builder
    public OrderBook(
        Long assetId,
        Long userId,
        Long orderId,
        BigDecimal amount,
        Long orderPriceKrw,
        BigDecimal assetBuyPrice,
        String orderType,
        Long orderCreationTime
    ) {
        this.id = assetId;
        this.userId = userId;
        this.orderId = orderId;
        this.amount = amount;
        this.orderPriceKrw = orderPriceKrw;
        this.assetBuyPrice = assetBuyPrice;
        this.orderType = orderType;
        this.orderCreationTime = orderCreationTime;
    }

    public OrderBookOrderItem toOrderBookDto() {
        return new OrderBookOrderItem(
            id, userId, orderId, amount, orderPriceKrw, assetBuyPrice, orderType, orderCreationTime
        );
    }
}
