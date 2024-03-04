package com.gamzabit.order.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.asset.DefaultAssetTypes;
import com.gamzabit.domain.order.OrderEntity.OrderType;
import com.gamzabit.domain.order.OrderReader;
import com.gamzabit.domain.order.vo.Order;
import com.gamzabit.domain.user.UserAssetReader;
import com.gamzabit.domain.user.vo.User;
import com.gamzabit.domain.user.vo.UserAsset;
import com.gamzabit.order.service.dto.OrderMessage;
import com.gamzabit.order.service.exception.OrderAlreadyCancelledException;
import com.gamzabit.order.service.exception.UserFundsException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderValidator {

    private final OrderReader orderReader;
    private final UserAssetReader userAssetReader;

    public void validateOrder(Long userId, OrderMessage order) {
        Long orderId = order.orderId();
        Order userOrder = orderReader.getUserOrder(userId, orderId);
        // 취소된 주문인지 검사한다.
        if (userOrder.orderType() == OrderType.Cancel) {
            throw new OrderAlreadyCancelledException(orderId);
        }
    }
}
