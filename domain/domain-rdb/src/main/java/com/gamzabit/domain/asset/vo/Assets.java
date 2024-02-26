package com.gamzabit.domain.asset.vo;

import java.math.BigDecimal;

import com.gamzabit.domain.asset.SymbolEntity;

public record Assets(
    BigDecimal currentValue,
    String symbolName,
    String symbolDisplayName,
    Boolean delisted
) {

    public static Assets from(SymbolEntity symbol) {
        return new Assets(
            symbol.getCurrentValue(),
            symbol.getSymbolName(),
            symbol.getSymbolDisplayName(),
            symbol.getDelisted()
        );
    }
}
