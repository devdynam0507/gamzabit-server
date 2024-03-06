package com.gamzabit.order.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.redis.orderbook.OrderBookReader;
import com.gamzabit.domain.redis.orderbook.dto.OrderOperationBoard;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderBookOperationReader {

    private final OrderBookReader orderBookReader;

    public OrderOperationBoard getOperationBoard(Long id, BigDecimal price) {
        return orderBookReader.getOrderOperationBoard(id, price);
    }
}
