package com.gamzabit.order.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.gamzabit.domain.redis.orderbook.dto.OrderBookCreate;
import com.gamzabit.domain.redis.orderbook.dto.OrderBookOrderItem;
import com.gamzabit.domain.redis.orderbook.dto.OrderResults;
import com.gamzabit.domain.redis.orderbook.dto.OrderTransaction;
import com.gamzabit.engine.DataSourceOrderEngine;
import com.gamzabit.engine.OrderPostProcessor;
import com.gamzabit.engine.OrderProcessorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisDataSourceOrderEngine implements DataSourceOrderEngine<OrderEngineIncomingMessage> {

    private final OrderProcessorAdapter<OrderResults, OrderEngineIncomingMessage> orderBookProcessor;
    private final OrderPostProcessor<OrderBookCreate> postProcessor;

    public RedisDataSourceOrderEngine(
        OrderProcessorAdapter<OrderResults, OrderEngineIncomingMessage> orderBookProcessor,
        OrderPostProcessor<OrderBookCreate> postProcessor
    ) {
        this.orderBookProcessor = orderBookProcessor;
        this.postProcessor = postProcessor;
    }

    void process(
        OrderEngineIncomingMessage orderProduceMessage,
        Function<OrderEngineIncomingMessage, OrderResults> adapterProcessor
    ) {
        OrderResults orderResults = adapterProcessor.apply(orderProduceMessage);

        List<OrderBookCreate> remainOrders = new ArrayList<>();
        if (orderResults.concludedOrders().isEmpty()) {
            remainOrders = List.of(orderProduceMessage.toOrderBookCreationDto());
        }
        if (!orderResults.isEmpty()) {
            remainOrders = orderResults.orderBranches().stream()
                .map(OrderBookOrderItem::toOrderBookCreationDto)
                .toList();
        }

        postProcessor.handle(remainOrders);
        sendTransactions(orderResults.concludedOrders());
    }

    public void buy(OrderEngineIncomingMessage orderMessage) {
        process(orderMessage, orderBookProcessor::buy);
    }

    @Override
    public void sell(OrderEngineIncomingMessage orderMessage) {
        process(orderMessage, orderBookProcessor::sell);
    }

    public void sendTransactions(List<OrderTransaction> transactions) {
        // TODO: send transaction alert
    }
}