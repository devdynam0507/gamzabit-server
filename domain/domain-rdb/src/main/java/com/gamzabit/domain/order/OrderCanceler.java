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
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderId);
        if (orderEntityOptional.isEmpty()) {
            throw new OrderNotFoundException(orderId);
        }
        OrderEntity order = orderEntityOptional.get();
        order.cancel();
    }
}
