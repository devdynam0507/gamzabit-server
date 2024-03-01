package com.gamzabit.domain.order;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.asset.AssetValidator;
import com.gamzabit.domain.order.vo.OrderCreate;
import com.gamzabit.domain.user.UserValidator;
import com.gamzabit.domain.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCreator {

    private final OrderRepository orderRepository;
    private final AssetValidator assetValidator;
    private final UserValidator userValidator;

    public Long createOrder(User user, OrderCreate orderCreate) {
        assetValidator.validateAssetExists(orderCreate.symbolId());
        userValidator.validateUserExists(user.id());
        OrderEntity order = orderCreate.toEntity(user.id());
        OrderEntity createdOrder = orderRepository.save(order);

        return createdOrder.getId();
    }
}
