package com.gamzabit.domain.order;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.order.exception.OrderNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCanceler {

    private final OrderRepository orderRepository;

    public void cancelOrder(Long userId, Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(orderId));
        order.cancel();
    }
}
