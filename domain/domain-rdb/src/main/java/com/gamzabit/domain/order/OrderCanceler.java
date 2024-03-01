package com.gamzabit.domain.order;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.order.exception.OrderNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCanceler {

    private final OrderRepository orderRepository;
    private final OrderTransactionRepository orderTransactionRepository;

    public void cancelOrder(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(orderId));
        order.cancel();
        OrderTransactionEntity transactionHistory = order.createTransactionHistory();
        orderTransactionRepository.save(transactionHistory);
    }
}
