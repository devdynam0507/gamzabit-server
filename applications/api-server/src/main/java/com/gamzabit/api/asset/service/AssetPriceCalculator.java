package com.gamzabit.api.asset.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.gamzabit.api.asset.domain.SymbolEntity;

@Service
public class AssetPriceCalculator {

    /**
     * 가지고 있는 가상 자산의 현재가격을 원화 가격으로 환산
     *
     * 가상자산의 현재가 * 갯수
     *
     * @param symbol 원화로 치환할 거래소 자산
     * @param amount 가상자산의 개수
     *
     * @return 가지고 있는 가상 자산의 현재 원화 가치
     * */
    public BigDecimal toKrw(SymbolEntity symbol, BigDecimal amount) {
        if (symbol.getSymbolName().equals("KRW")) {
            return amount;
        }
        return symbol.getCurrentValue().multiply(amount);
    }

    /**
     * 가지고 있는 가상 자산의 현재가격을 원화 가격으로 환산
     *
     * 구매가 / 가상자산의 현재가
     *
     * @param symbol 구매 가능한 개수 치환할 거래소 자산
     * @param buyPrice 구매할 가격
     *
     * @return 가상 자산의 갯수를 구매할 갯수만큼 환산
     * */
    public BigDecimal toKrwPerPiece(SymbolEntity symbol, BigDecimal buyPrice) {
        return buyPrice.divide(symbol.getCurrentValue(), 30, RoundingMode.HALF_UP);
    }
}
