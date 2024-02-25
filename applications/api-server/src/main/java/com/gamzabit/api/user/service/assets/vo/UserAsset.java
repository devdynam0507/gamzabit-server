package com.gamzabit.api.user.service.assets.vo;

import java.math.BigDecimal;

import com.gamzabit.api.asset.domain.SymbolEntity;
import com.gamzabit.api.user.domain.UserAssetEntity;

public record UserAsset(
    BigDecimal amount,
    String symbolName,
    String symbolDisplayName,
    Boolean delisted
) {

    public static UserAsset from(UserAssetEntity userAssetEntity) {
        SymbolEntity symbolEntity = userAssetEntity.getSymbol();
        return new UserAsset(
            userAssetEntity.getAmount(),
            symbolEntity.getSymbolName(),
            symbolEntity.getSymbolDisplayName(),
            symbolEntity.getDelisted()
        );
    }
}
