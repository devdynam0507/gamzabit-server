package com.gamzabit.api.asset.service;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

import com.gamzabit.domain.asset.vo.Assets;

class AssetPriceCalculatorTest {

    private final AssetPriceCalculator assetPriceCalculator = new AssetPriceCalculator();

    @Test
    public void 보유중인_가상화폐를_원화로_치환() {
        Assets exchangeAsset = new Assets(
            BigDecimal.valueOf(68_655_109.60),
            "BTC",
            "비트코인",
            false
        );
        // 보유중인 비트코인 갯수
        BigDecimal amount = BigDecimal.valueOf(0.00123);

        BigDecimal krw = assetPriceCalculator.toKrw(exchangeAsset, amount);

        assertThat(krw.compareTo(BigDecimal.valueOf(84_445.784808))).isZero();
    }

    @Test
    public void 구매할_금액만큼의_가상자산_개수_구하기() {
        Assets exchangeAsset = new Assets(
            BigDecimal.valueOf(68_655_109.60),
            "BTC",
            "비트코인",
            false
        );
        // 구매할 가격 84,445.78원
        BigDecimal buyPrice = BigDecimal.valueOf(84_445.784808);

        // 84,445.78원 어치만큼의 가상자산 갯수
        BigDecimal assetAmount = assetPriceCalculator.toKrwPerPiece(exchangeAsset, buyPrice)
            .setScale(6, RoundingMode.HALF_UP);

        assertThat(assetAmount.compareTo(BigDecimal.valueOf(0.00123))).isZero();
    }

    @Test
    public void 보유금액보다_낮은_금액의_가상자산_원화변환_시나리오() {
        Assets exchangeAsset = new Assets(
            BigDecimal.valueOf(201.6),
            "ETH",
            "알트코인",
            false
        );
        BigDecimal amount = BigDecimal.valueOf(100);

        BigDecimal krw = assetPriceCalculator.toKrw(exchangeAsset, amount);

        assertThat(krw.compareTo(BigDecimal.valueOf(20160))).isZero();
    }

    @Test
    public void 보유금액보다_낮은_금액의_가상자산_구매_시나리오() {
        Assets exchangeAsset = new Assets(
            BigDecimal.valueOf(201.6),
            "ETH",
            "알트코인",
            false
        );
        // 30,000원 어치만큼의 가상자산 갯수
        BigDecimal buyPrice = BigDecimal.valueOf(30000);

        BigDecimal assetAmount = assetPriceCalculator.toKrwPerPiece(exchangeAsset, buyPrice)
            .setScale(6, RoundingMode.HALF_UP);
        System.out.println(assetAmount);

        assertThat(assetAmount.compareTo(BigDecimal.valueOf(148.809524))).isZero();
    }

    @Test
    public void 원화_치환() {
        Assets exchangeAsset = new Assets(
            BigDecimal.valueOf(0),
            "KRW",
            "원",
            false
        );
        BigDecimal amount = BigDecimal.valueOf(718_000);

        BigDecimal krw = assetPriceCalculator.toKrw(exchangeAsset, amount);

        assertThat(krw.compareTo(BigDecimal.valueOf(718_000))).isZero();
    }
}