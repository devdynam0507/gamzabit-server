package com.gamzabit.api.order.service;

import org.springframework.stereotype.Service;

import com.gamzabit.api.order.validator.OrderValidationException;
import com.gamzabit.domain.asset.DefaultAssetTypes;
import com.gamzabit.domain.order.OrderCanceler;
import com.gamzabit.domain.order.OrderEntity.OrderState;
import com.gamzabit.domain.order.OrderReader;
import com.gamzabit.domain.order.vo.Order;
import com.gamzabit.domain.user.UserAssetFreezeProcessor;
import com.gamzabit.domain.user.UserReader;
import com.gamzabit.domain.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCancelService {

    private final OrderCanceler orderCanceler;
    private final UserAssetFreezeProcessor assetFreezeProcessor;
    private final UserReader userReader;
    private final OrderReader orderReader;

    public void cancelOrder(Long userId, Long orderId) {
        User user = userReader.findUserById(userId);
        Order order = orderReader.getUserOrder(userId, orderId);
        OrderState orderState = order.orderState();
        if (orderState == OrderState.Cancel) {
            throw new OrderValidationException("이미 주문이 취소되었습니다.", orderId);
        }
        if (orderState == OrderState.Available || orderState == OrderState.Concluded) {
            throw new OrderValidationException("이미 주문이 체결되었습니다.", orderId);
        }

        orderCanceler.cancelOrder(orderId);
        assetFreezeProcessor.unfreeze(user, order.id(), DefaultAssetTypes.KRW.name(), order.orderPrice());
    }
}
