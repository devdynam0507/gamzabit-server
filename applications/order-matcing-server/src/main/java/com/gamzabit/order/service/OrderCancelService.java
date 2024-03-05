package com.gamzabit.order.service;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.redis.orderbook.OrderBookProcessor;
import com.gamzabit.domain.redis.orderbook.dto.OrderBookCreate;
import com.gamzabit.order.consumers.dto.OrderCancelMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCancelService {

    private final OrderBookProcessor orderBookProcessor;

    public void cancelOrder(OrderCancelMessage orderCancelMessage) {
        OrderBookCreate orderBookCreate = new OrderBookCreate(
            orderCancelMessage.assetId(),
            orderCancelMessage.userId(),
            orderCancelMessage.orderId(),
            null,
            null,
            null,
            orderCancelMessage.previousOrderType().name(),
            null
        );

        orderBookProcessor.deleteOrder(orderBookCreate);
    }
}
