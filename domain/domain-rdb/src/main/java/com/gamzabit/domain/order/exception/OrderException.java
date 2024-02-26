package com.gamzabit.domain.order.exception;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {

    private final Long orderId;

    public OrderException(String message, Long orderId) {
        super(message);
        this.orderId = orderId;
    }
}
