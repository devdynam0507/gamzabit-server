package com.gamzabit.api.order.service;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.order.OrderCanceler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCancelService {

    private final OrderCanceler orderCanceler;

    public void cancelOrder(Long userId, Long orderId) {
        orderCanceler.cancelOrder(userId, orderId);
    }
}
