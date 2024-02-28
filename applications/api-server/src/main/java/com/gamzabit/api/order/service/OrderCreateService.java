package com.gamzabit.api.order.service;

import org.springframework.stereotype.Service;

import com.gamzabit.api.order.service.dto.OrderProduceMessage;
import com.gamzabit.api.order.validator.OrderValidator;
import com.gamzabit.domain.order.OrderCreator;
import com.gamzabit.domain.order.vo.OrderCreate;
import com.gamzabit.domain.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

    private final OrderCreator orderCreator;
    private final OrderProducer orderProducer;
    private final OrderValidator orderValidator;

    public Long createOrder(User user, OrderCreate orderCreate) {
        orderValidator.validateOrder(user, orderCreate);

        Long orderId = orderCreator.createOrder(user, orderCreate);
        Long now = System.currentTimeMillis();
        orderProducer.send(OrderProduceMessage.from(user.id(), orderCreate, orderId, now));

        return orderId;
    }
}
