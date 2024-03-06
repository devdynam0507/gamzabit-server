package com.gamzabit.api.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gamzabit.api.order.consumer.dto.OrderTransactionMessage;
import com.gamzabit.domain.order.OrderProcessor;
import com.gamzabit.domain.order.vo.OrderTransaction;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderTransactionService {

    private final OrderProcessor orderProcessor;

    public void handleTransaction(OrderTransactionMessage message) {
        List<OrderTransaction> orderTransactions = message.transactions().stream()
            .map(orderTransactionItem -> new OrderTransaction(
                orderTransactionItem.userId(),
                orderTransactionItem.orderId(),
                orderTransactionItem.assetId(),
                orderTransactionItem.orderState(),
                orderTransactionItem.orderType(),
                orderTransactionItem.concludedAmount(),
                orderTransactionItem.concludedPrice(),
                orderTransactionItem.concludedTime()
            ))
            .collect(Collectors.toCollection(ArrayList::new));

        orderProcessor.handleConcludeOrder(orderTransactions);
    }
}
