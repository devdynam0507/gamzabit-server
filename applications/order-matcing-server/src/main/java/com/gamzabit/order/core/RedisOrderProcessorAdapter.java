package com.gamzabit.order.core;

import java.util.List;

import com.gamzabit.domain.redis.orderbook.OrderBookProcessor;
import com.gamzabit.domain.redis.orderbook.dto.OrderBookCreate;
import com.gamzabit.domain.redis.orderbook.dto.OrderResults;
import com.gamzabit.order.consumers.dto.OrderProduceMessage;

public class RedisOrderProcessorAdapter extends AbstractOrderProcessorAdapter<OrderResults, OrderBookCreate> {

    private final OrderBookProcessor orderBookProcessor;

    public RedisOrderProcessorAdapter(OrderBookProcessor orderBookProcessor) {
        this.orderBookProcessor = orderBookProcessor;
    }

    @Override
    public OrderResults buy(OrderBookCreate newOrderBook) {
        return orderBookProcessor.buy(newOrderBook);
    }

    @Override
    public OrderResults sell(OrderBookCreate orderMessage) {
        return orderBookProcessor.sell(orderMessage);
    }

    @Override
    public void saveBuy(OrderBookCreate orderMessage) {
        orderBookProcessor.saveOrder(orderMessage);
    }

    @Override
    public void saveBuys(List<OrderBookCreate> orderMessages) {
        orderBookProcessor.saveAllOrders(orderMessages);
    }
}
