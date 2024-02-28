package com.gamzabit.api.order.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.gamzabit.api.asset.service.AssetPriceCalculator;
import com.gamzabit.api.user.service.UserAssetAggregator;
import com.gamzabit.api.utils.BigDecimalComparator;
import com.gamzabit.domain.asset.SymbolReader;
import com.gamzabit.domain.asset.exception.AssetException;
import com.gamzabit.domain.asset.vo.Assets;
import com.gamzabit.domain.order.vo.OrderCreate;
import com.gamzabit.domain.user.vo.User;
import com.gamzabit.domain.user.vo.UserAssetWithKrw;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderValidator {

    private final UserAssetAggregator userAssetAggregator;
    private final AssetPriceCalculator assetPriceCalculator;
    private final SymbolReader symbolReader;

    public void validateOrder(User user, OrderCreate orderCreate) {
        Assets exchangeAsset = symbolReader.getSymbolById(orderCreate.symbolId());
        if (exchangeAsset.delisted()) {
            throw new AssetException("상장폐지된 종목 입니다.");
        }
        UserAssetWithKrw userKrwAsset = userAssetAggregator.aggregateAssetWithKrw(user, "KRW");
        BigDecimal userOwnKrw = userKrwAsset.krw();
        BigDecimal buyAssetKrw = assetPriceCalculator.toKrw(exchangeAsset, orderCreate.amount());

        if (!BigDecimalComparator.of(userOwnKrw).equalsOrBiggerThen(buyAssetKrw)) {
            throw new OrderValidationException("자금이 부족합니다.", null);
        }
    }
}
