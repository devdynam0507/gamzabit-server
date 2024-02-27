package com.gamzabit.api.order.service;

import org.springframework.stereotype.Service;

import com.gamzabit.api.order.service.dto.OrderProduceMessage;
import com.gamzabit.domain.order.OrderCreator;
import com.gamzabit.domain.order.vo.OrderCreate;
import com.gamzabit.domain.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

    private final OrderCreator orderCreator;
    private final OrderProducer orderProducer;

    public Long createOrder(User user, OrderCreate orderCreate) {
        Long orderId = orderCreator.createOrder(user, orderCreate);
        orderProducer.send(OrderProduceMessage.from(user.id(), orderCreate, orderId));

        return orderId;
    }
}
