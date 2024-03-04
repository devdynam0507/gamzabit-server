package com.gamzabit.order.core;

import java.util.List;

import com.gamzabit.domain.redis.orderbook.dto.OrderBookCreate;
import com.gamzabit.engine.OrderPostProcessor;

public class RedisOrderPostProcessor implements OrderPostProcessor<OrderBookCreate> {

    private final RedisOrderProcessorAdapter orderProcessorAdapter;

    public RedisOrderPostProcessor(RedisOrderProcessorAdapter orderProcessorAdapter) {
        this.orderProcessorAdapter = orderProcessorAdapter;
    }

    @Override
    public void handle(List<OrderBookCreate> orderBookCreates) {
        orderProcessorAdapter.saveAllOrders(orderBookCreates);
    }
}
