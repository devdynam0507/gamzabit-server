package com.gamzabit.order.core;

import java.util.List;

import com.gamzabit.domain.redis.orderbook.OrderBookProcessor;
import com.gamzabit.domain.redis.orderbook.dto.OrderBookCreate;
import com.gamzabit.domain.redis.orderbook.dto.OrderResults;
import com.gamzabit.engine.OrderProcessorAdapter;

public class RedisOrderProcessorAdapter implements OrderProcessorAdapter<OrderResults, OrderEngineIncomingMessage> {

    private final OrderBookProcessor orderBookProcessor;

    public RedisOrderProcessorAdapter(OrderBookProcessor orderBookProcessor) {
        this.orderBookProcessor = orderBookProcessor;
    }

    @Override
    public OrderResults buy(OrderEngineIncomingMessage orderMessage) {
        return orderBookProcessor.buy(orderMessage.toOrderBookCreationDto());
    }

    @Override
    public OrderResults sell(OrderEngineIncomingMessage orderMessage) {
        return orderBookProcessor.sell(orderMessage.toOrderBookCreationDto());
    }

    @Override
    public void save(OrderEngineIncomingMessage orderMessage) {
        orderBookProcessor.saveOrder(orderMessage.toOrderBookCreationDto());
    }

    @Override
    public void saveAll(List<OrderEngineIncomingMessage> orderMessages) {
        List<OrderBookCreate> orderBookCreates = orderMessages.stream()
            .map(OrderEngineIncomingMessage::toOrderBookCreationDto)
            .toList();

        saveAllOrders(orderBookCreates);
    }

    public void saveAllOrders(List<OrderBookCreate> orderBookCreates) {
        orderBookProcessor.saveAllOrders(orderBookCreates);
    }
}
