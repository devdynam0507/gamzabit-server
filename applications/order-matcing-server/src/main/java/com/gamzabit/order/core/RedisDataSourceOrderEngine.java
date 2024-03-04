package com.gamzabit.order.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.gamzabit.domain.redis.orderbook.dto.OrderBookCreate;
import com.gamzabit.domain.redis.orderbook.dto.OrderResults;
import com.gamzabit.domain.redis.orderbook.dto.OrderTransaction;
import com.gamzabit.order.consumers.dto.OrderProduceMessage;

public class RedisDataSourceOrderEngine implements DataSourceOrderEngine {

    private final OrderProcessorAdapter<OrderResults, OrderBookCreate> orderBookProcessor;

    public RedisDataSourceOrderEngine(OrderProcessorAdapter<OrderResults, OrderBookCreate> orderBookProcessor) {
        this.orderBookProcessor = orderBookProcessor;
    }

    void process(OrderProduceMessage orderProduceMessage, Function<OrderBookCreate, OrderResults> adapterProcessor) {
        List<OrderBookCreate> orderBookOrderItems = new ArrayList<>();
        List<OrderTransaction> transactions = new ArrayList<>();
        List<OrderBookCreate> remainOrders = new ArrayList<>();

        orderBookOrderItems.add(orderProduceMessage.toOrderBookCreationDto());

        // 1. orderBookOrderItems를 순회한다.
        // 2. 구매, 판매 로직을 실행한다. 이 때 모든 거래가 완료되지 않은 경우 (판매 수량이 쪼개졌다거나) remainOrders 에 주문을 다시 추가한다.
        // 3. 만약 주문 처리 결과가 없다면 남은 주문에 추가하고 끝낸다.
        // 4. 거래 결과를 모두 저장한다.
        // 5. 거래 결과를 거래 서버에 알린다.

        for (OrderBookCreate orderBookCreateDto : orderBookOrderItems) {
            OrderResults orderResults = adapterProcessor.apply(orderBookCreateDto);
            // 만약 처리 결과가 없을 경우 구매 목록에 저장
            if (orderResults.isEmpty()) {
                remainOrders.add(orderBookCreateDto);
                continue;
            }
            // 거래 결과 추가
            transactions.addAll(orderResults.concludedOrders());
            // 분기된 거래를 다시 추가
            orderResults.orderBranches().forEach(remainOrder -> remainOrders.add(remainOrder.toOrderBookCreationDto()));
        }

        // 처리가 끝나고 남은 주문을 레디스에 저장한다.
        if (!remainOrders.isEmpty()) {
            orderBookProcessor.saveBuys(remainOrders);
        }
        sendTransactions(transactions);
    }

    public void buy(OrderProduceMessage orderMessage) {
        process(orderMessage, orderBookProcessor::buy);
    }

    @Override
    public void sell(OrderProduceMessage orderMessage) {
        process(orderMessage, orderBookProcessor::sell);
    }

    public void sendTransactions(List<OrderTransaction> transactions) {
        // TODO: send transaction alert
    }
}
