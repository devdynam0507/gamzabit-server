package com.gamzabit.order.service.exception;

import lombok.Getter;

@Getter
public class OrderValidationException extends RuntimeException {

    private final Long orderId;

    public OrderValidationException(String message, Long orderId) {
        super(message);
        this.orderId = orderId;
    }
}
