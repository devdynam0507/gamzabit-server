package com.gamzabit.api.order.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.gamzabit.api.order.service.dto.OrderProduceMessage;
import com.gamzabit.api.order.validator.OrderValidator;
import com.gamzabit.domain.asset.AssetPrice;
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

    public Long createSellOrder(User user, OrderCreate orderCreate) {
        UserAsset userAsset = userAssetReader.getSpecificSymbolUserAsset(user, orderCreate.symbolId());
        orderValidator.validateSellOrder(orderCreate, userAsset);

        Long orderId = orderCreator.createOrder(user, orderCreate);
        String assetName = userAsset.assets().symbolName();

        AssetPrice freezeAmount = new AssetPrice(orderCreate.amount());
        userAssetFreezeProcessor.doFreeze(
            orderCreate,
            user,
            orderId,
            userAsset,
            freezeAmount,
            assetName
        );
        Long now = System.currentTimeMillis();
        orderProducer.send(OrderProduceMessage.from(user.id(), orderCreate, orderId, now));

        return orderId;
    }

    public Long createBuyOrder(User user, OrderCreate orderCreate) {
        orderValidator.validateBuyOrder(user, orderCreate);

        String krwAssetName = DefaultAssetTypes.KRW.name();
        // 주문을 생성하고
        Long orderId = orderCreator.createOrder(user, orderCreate);
        UserAsset userAsset = userAssetReader.getSpecificSymbolUserAsset(user, krwAssetName);

        AssetPrice freezeAmount = new AssetPrice(BigDecimal.valueOf(orderCreate.orderPriceKrw()));
        // 유저의 생성된 주문만큼의 유저의 자산을 동결한다.
        userAssetFreezeProcessor.doFreeze(
            orderCreate,
            user,
            orderId,
            userAsset,
            freezeAmount,
            krwAssetName
        );
        Long now = System.currentTimeMillis();
        orderProducer.send(OrderProduceMessage.from(user.id(), orderCreate, orderId, now));

        return orderId;
    }

    public Long createOrder(User user, OrderCreate orderCreate) {
        return switch (orderCreate.orderType()) {
            case Buy -> createBuyOrder(user, orderCreate);
            case Sell -> createSellOrder(user, orderCreate);
            default -> throw new UnsupportedOperationException("지원되지 않는 주문 입니다.");
        };
    }
}
