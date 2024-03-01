package com.gamzabit.api.order.service;

import org.springframework.stereotype.Service;

import com.gamzabit.api.order.service.dto.OrderProduceMessage;
import com.gamzabit.api.order.validator.OrderValidator;
import com.gamzabit.domain.asset.DefaultAssetTypes;
import com.gamzabit.domain.order.OrderCreator;
import com.gamzabit.domain.order.vo.OrderCreate;
import com.gamzabit.domain.user.UserAssetFreezeProcessor;
import com.gamzabit.domain.user.UserAssetProcessor;
import com.gamzabit.domain.user.UserAssetReader;
import com.gamzabit.domain.user.vo.User;
import com.gamzabit.domain.user.vo.UserAsset;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

    private final OrderCreator orderCreator;
    private final OrderProducer orderProducer;
    private final OrderValidator orderValidator;
    private final UserAssetFreezeProcessor userAssetFreezeProcessor;
    private final UserAssetReader userAssetReader;
    private final UserAssetProcessor userAssetProcessor;

    public Long createOrder(User user, OrderCreate orderCreate) {
        orderValidator.validateOrder(user, orderCreate);

        String krwAssetName = DefaultAssetTypes.KRW.name();
        // 주문을 생성하고
        Long orderId = orderCreator.createOrder(user, orderCreate);
        UserAsset userAsset = userAssetReader.getSpecificSymbolUserAsset(user, krwAssetName);

        // 유저의 생성된 주문만큼의 유저의 자산을 동결한다.
        userAssetFreezeProcessor.doFreeze(
            orderCreate,
            user,
            orderId,
            userAsset,
            krwAssetName
        );
        // 유저의 자산을 차감한다.
        userAssetProcessor.withdrawTo(user, orderCreate.toAssetPrice(), krwAssetName);
        Long now = System.currentTimeMillis();
        orderProducer.send(OrderProduceMessage.from(user.id(), orderCreate, orderId, now));

        return orderId;
    }
}
