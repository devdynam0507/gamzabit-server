package com.gamzabit.api.order.service;

import org.springframework.stereotype.Service;

import com.gamzabit.api.order.domain.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder() {

    }
}
