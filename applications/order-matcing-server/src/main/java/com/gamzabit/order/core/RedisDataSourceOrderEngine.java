package com.gamzabit.order.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.gamzabit.domain.redis.orderbook.dto.OrderBookCreate;
import com.gamzabit.domain.redis.orderbook.dto.OrderBookOrderItem;
import com.gamzabit.domain.redis.orderbook.dto.OrderOperationBoard;
import com.gamzabit.domain.redis.orderbook.dto.OrderResults;
import com.gamzabit.domain.redis.orderbook.dto.OrderTransaction;
import com.gamzabit.engine.DataSourceOrderEngine;
import com.gamzabit.engine.OrderPostProcessor;
import com.gamzabit.engine.OrderProcessorAdapter;
import com.gamzabit.order.service.OrderBookOperationReader;
import com.gamzabit.order.service.OrderTransactionProducer;
import com.gamzabit.order.service.dto.OrderTransactionItem;
import com.gamzabit.order.service.dto.OrderTransactionMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisDataSourceOrderEngine implements DataSourceOrderEngine<OrderEngineIncomingMessage> {

    private final OrderProcessorAdapter<OrderResults, OrderEngineIncomingMessage> orderBookProcessor;
    private final OrderPostProcessor<OrderBookCreate> postProcessor;
    private final OrderTransactionProducer orderTransactionProducer;
    private final OrderBookOperationReader orderBookOperationReader;

    public RedisDataSourceOrderEngine(
        OrderProcessorAdapter<OrderResults, OrderEngineIncomingMessage> orderBookProcessor,
        OrderPostProcessor<OrderBookCreate> postProcessor,
        OrderTransactionProducer orderTransactionProducer,
        OrderBookOperationReader orderBookOperationReader
    ) {
        this.orderBookProcessor = orderBookProcessor;
        this.postProcessor = postProcessor;
        this.orderTransactionProducer = orderTransactionProducer;
        this.orderBookOperationReader = orderBookOperationReader;
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
        List<OrderTransaction> transactions = orderResults.concludedOrders();
        if (!transactions.isEmpty()) {
            sendTransactions(transactions);
            // TODO: 시장 데이터 서버에 거래내역 전파 (차트를 위한)
        }
        Long symbolId = orderProduceMessage.symbolId();
        BigDecimal price = orderProduceMessage.assetBuyPriceKrw();
        OrderOperationBoard operationBoard = orderBookOperationReader.getOperationBoard(symbolId, price);

        log.info("operation board: {}", operationBoard);
    }

    public void buy(OrderEngineIncomingMessage orderMessage) {
        process(orderMessage, orderBookProcessor::buy);
    }

    @Override
    public void sell(OrderEngineIncomingMessage orderMessage) {
        process(orderMessage, orderBookProcessor::sell);
    }

    public void sendTransactions(List<OrderTransaction> transactions) {
        List<OrderTransactionItem> transactionMessageItems = transactions.stream()
            .map(OrderTransactionItem::from)
            .toList();

        orderTransactionProducer.send(new OrderTransactionMessage(transactionMessageItems));
    }
}
