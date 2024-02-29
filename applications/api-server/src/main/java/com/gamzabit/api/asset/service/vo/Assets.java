package com.gamzabit.api.asset.service.vo;

import java.math.BigDecimal;

import com.gamzabit.domain.asset.AssetEntity;

public record Assets(
    BigDecimal currentValue,
    String symbolName,
    String symbolDisplayName,
    Boolean delisted
) {

    public static Assets from(AssetEntity symbol) {
        return new Assets(
            symbol.getCurrentValue(),
            symbol.getSymbolName(),
            symbol.getSymbolDisplayName(),
            symbol.getDelisted()
        );
    }
}
