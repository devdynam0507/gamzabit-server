package com.gamzabit.api.order.validator;

import com.gamzabit.domain.order.exception.OrderException;

public class OrderValidationException extends OrderException {

    public OrderValidationException(String message, Long orderId) {
        super(message, orderId);
    }
}
