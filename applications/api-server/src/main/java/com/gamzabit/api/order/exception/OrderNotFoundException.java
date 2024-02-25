package com.gamzabit.api.order.exception;

public class OrderNotFoundException extends OrderException {

    public OrderNotFoundException(Long orderId, Long userId) {
        super("주문을 찾을 수 없습니다.", orderId, userId);
    }
}
