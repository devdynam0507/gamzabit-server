package com.gamzabit.api.order.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamzabit.api.asset.domain.SymbolRepository;
import com.gamzabit.api.asset.exception.AssetNotFoundException;
import com.gamzabit.api.order.domain.OrderEntity;
import com.gamzabit.api.order.domain.OrderRepository;
import com.gamzabit.api.order.domain.types.OrderState;
import com.gamzabit.api.order.domain.types.OrderType;
import com.gamzabit.api.order.service.vo.OrderCreate;
import com.gamzabit.api.user.exception.UserNotFoundException;
import com.gamzabit.api.user.service.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final SymbolRepository symbolRepository;

    @Transactional
    public Long createOrder(User user, OrderCreate orderCreate) {
        Long symbolId = orderCreate.symbolId();
        boolean isExistsSymbol = symbolRepository.existsById(symbolId);
        if (!isExistsSymbol) {
            throw new AssetNotFoundException("존재하지 않는 심볼입니다.", String.valueOf(symbolId));
        }
        if (user.deleted()) {
            throw new UserNotFoundException(user.nickname());
        }
        OrderEntity order = orderCreate.toEntity(user);
        OrderEntity createdOrder = orderRepository.save(order);

        return createdOrder.getId();
    }
}
