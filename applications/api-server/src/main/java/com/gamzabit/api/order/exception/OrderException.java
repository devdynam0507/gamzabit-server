package com.gamzabit.api.order.exception;

public class OrderException extends RuntimeException {

    private Long orderId, userId;

    public OrderException(String message, Long orderId, Long userId) {
        super(message);
        this.orderId = orderId;
        this.userId = userId;
    }
}
