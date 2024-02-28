package com.gamzabit.order.service;

import org.springframework.stereotype.Service;

import com.gamzabit.order.service.dto.Order;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderValidator {

    public void validateOrder(Long userId, Order order) {
        // 주문이 취소되었는지 검사한다.
    }
}
