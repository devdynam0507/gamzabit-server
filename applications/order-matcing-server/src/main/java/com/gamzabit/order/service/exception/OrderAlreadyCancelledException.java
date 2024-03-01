package com.gamzabit.order.service.exception;

public class OrderAlreadyCancelledException extends OrderValidationException {

    public OrderAlreadyCancelledException(Long orderId) {
        super("이미 취소된 주문 입니다.", orderId);
    }
}
