package com.gamzabit.api.order.service;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.order.service.OrderCreator;
import com.gamzabit.domain.order.vo.OrderCreate;
import com.gamzabit.domain.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

    private final OrderCreator orderCreator;

    public Long createOrder(User user, OrderCreate orderCreate) {
        return orderCreator.createOrder(user, orderCreate);
    }
}