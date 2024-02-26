package com.gamzabit.domain.order.exception;

public class OrderNotFoundException extends OrderException {

    public OrderNotFoundException(Long orderId) {
        super("주문을 찾을 수 없습니다.", orderId);
    }
}
