package com.gamzabit.order.service.exception;

public class UserFundsException extends OrderValidationException {

    public UserFundsException(Long orderId) {
        super("유저가 주문한 금액보다 가지고 있는 금액이 적습니다.", orderId);
    }
}
