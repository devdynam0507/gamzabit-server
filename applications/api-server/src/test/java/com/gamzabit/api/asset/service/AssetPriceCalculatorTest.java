package com.gamzabit.api.asset.service;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

import com.gamzabit.api.asset.domain.SymbolEntity;

class AssetPriceCalculatorTest {

    private final AssetPriceCalculator assetPriceCalculator = new AssetPriceCalculator();

    @Test
    public void 보유중인_가상화폐를_원화로_치환() {
        SymbolEntity exchangeAsset = SymbolEntity.builder()
            .symbolName("BTC")
            .symbolDisplayName("비트코인")
            .currentValue(BigDecimal.valueOf(68_655_109.60))
            .build();
        // 보유중인 비트코인 갯수
        BigDecimal amount = BigDecimal.valueOf(0.00123);

        BigDecimal krw = assetPriceCalculator.toKrw(exchangeAsset, amount);

        assertThat(krw.compareTo(BigDecimal.valueOf(84_445.784808))).isZero();
    }

    @Test
    public void 구매할_금액만큼의_가상자산_개수_구하기() {
        SymbolEntity exchangeAsset = SymbolEntity.builder()
            .symbolDisplayName("비트코인")
            .currentValue(BigDecimal.valueOf(68_655_109.60))
            .build();
        // 구매할 가격 84,445.78원
        BigDecimal buyPrice = BigDecimal.valueOf(84_445.784808);

        // 84,445.78원 어치만큼의 가상자산 갯수
        BigDecimal assetAmount = assetPriceCalculator.toKrwPerPiece(exchangeAsset, buyPrice)
            .setScale(6, RoundingMode.HALF_UP);

        assertThat(assetAmount.compareTo(BigDecimal.valueOf(0.00123))).isZero();
    }

    @Test
    public void 보유금액보다_낮은_금액의_가상자산_원화변환_시나리오() {
        SymbolEntity exchangeAsset = SymbolEntity.builder()
           .symbolName("ETH")
           .symbolDisplayName("알트코인")
           .currentValue(BigDecimal.valueOf(201.6))
           .build();
        BigDecimal amount = BigDecimal.valueOf(100);

        BigDecimal krw = assetPriceCalculator.toKrw(exchangeAsset, amount);

        assertThat(krw.compareTo(BigDecimal.valueOf(20160))).isZero();
    }

    @Test
    public void 보유금액보다_낮은_금액의_가상자산_구매_시나리오() {
        SymbolEntity exchangeAsset = SymbolEntity.builder()
            .symbolName("ETH")
            .symbolDisplayName("알트코인")
            .currentValue(BigDecimal.valueOf(201.6))
            .build();

        // 30,000원 어치만큼의 가상자산 갯수
        BigDecimal buyPrice = BigDecimal.valueOf(30000);

        BigDecimal assetAmount = assetPriceCalculator.toKrwPerPiece(exchangeAsset, buyPrice)
            .setScale(6, RoundingMode.HALF_UP);
        System.out.println(assetAmount);

        assertThat(assetAmount.compareTo(BigDecimal.valueOf(148.809524))).isZero();
    }

    @Test
    public void 원화_치환() {
        SymbolEntity exchangeAsset = SymbolEntity.builder()
            .symbolName("KRW")
            .symbolDisplayName("원화")
            .currentValue(BigDecimal.valueOf(0))
            .build();
        BigDecimal amount = BigDecimal.valueOf(718_000);

        BigDecimal krw = assetPriceCalculator.toKrw(exchangeAsset, amount);

        assertThat(krw.compareTo(BigDecimal.valueOf(718_000))).isZero();
    }
}