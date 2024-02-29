package com.gamzabit.api.order.validator;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.order.vo.OrderCreate;
import com.gamzabit.domain.user.AssetAmount;
import com.gamzabit.domain.user.UserAssetCalculator;
import com.gamzabit.domain.user.vo.User;
import com.gamzabit.domain.user.vo.UserAssetWithKrw;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderValidator {

    private final UserAssetCalculator userAssetCalculator;

    public void validateOrder(User user, OrderCreate orderCreate) {
        UserAssetWithKrw ownKrw = userAssetCalculator.getAssetsKrwPrice(user);
        AssetPrice buyPrice = userAssetCalculator.calculateBuyPrice(
            new AssetAmount(orderCreate.amount()), orderCreate.symbolId());

        if (ownKrw.krw().number().isLessThan(buyPrice.number())) {
            throw new OrderValidationException("자금이 부족합니다.", null);
        }
    }
}
