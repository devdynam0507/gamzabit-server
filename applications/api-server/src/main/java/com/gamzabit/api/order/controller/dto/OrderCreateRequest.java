package com.gamzabit.api.order.controller.dto;

import java.math.BigDecimal;

import com.gamzabit.domain.order.OrderEntity;
import com.gamzabit.domain.order.vo.OrderCreate;

import lombok.Data;

@Data
public class OrderCreateRequest {

    private Long symbolId;
    private BigDecimal amount;
    private Long orderPriceKrw;
    private OrderEntity.OrderType orderType;

    public OrderCreate toOrderCreate() {
        return new OrderCreate(symbolId, amount, orderPriceKrw, orderType);
    }
}
