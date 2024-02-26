package com.gamzabit.domain.order;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.asset.SymbolRepository;
import com.gamzabit.domain.asset.exception.AssetNotFoundException;
import com.gamzabit.domain.order.OrderEntity;
import com.gamzabit.domain.order.OrderRepository;
import com.gamzabit.domain.order.vo.OrderCreate;
import com.gamzabit.domain.user.exception.UserNotFoundException;
import com.gamzabit.domain.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCreator {

    private final OrderRepository orderRepository;
    private final SymbolRepository symbolRepository;

    public Long createOrder(User user, OrderCreate orderCreate) {
        Long symbolId = orderCreate.symbolId();
        boolean isExistsSymbol = symbolRepository.existsById(symbolId);
        if (!isExistsSymbol) {
            throw new AssetNotFoundException("존재하지 않는 심볼입니다.", String.valueOf(symbolId));
        }
        if (user.deleted()) {
            throw new UserNotFoundException(user.nickname());
        }
        OrderEntity order = orderCreate.toEntity(user.id());
        OrderEntity createdOrder = orderRepository.save(order);

        return createdOrder.getId();
    }
}
