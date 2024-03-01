package com.gamzabit.api.order.service;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.asset.AssetReader;
import com.gamzabit.domain.asset.vo.Assets;
import com.gamzabit.domain.order.OrderCanceler;
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
    private final AssetReader assetReader;

    public void cancelOrder(Long userId, Long orderId) {
        User user = userReader.findUserById(userId);
        Order order = orderReader.getUserOrder(user, orderId);
        Assets asset = assetReader.getSymbolById(order.assetId());

        orderCanceler.cancelOrder(userId, orderId);
        assetFreezeProcessor.unfreeze(user, order.id(), asset.symbolName(), order.orderPrice());
    }
}
