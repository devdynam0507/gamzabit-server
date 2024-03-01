package com.gamzabit.domain.asset;

import java.math.BigDecimal;

import com.gamzabit.domain.asset.vo.Assets;
import com.gamzabit.domain.common.EntityBase;
import com.gamzabit.domain.user.AssetAmount;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "asset")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class AssetEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AssetPrice assetPrice = new AssetPrice(BigDecimal.ZERO);

    @Embedded
    private Symbol symbol;

    @Enumerated(EnumType.STRING)
    private ListedType listedType;

    @Builder
    public AssetEntity(AssetPrice assetPrice, Symbol symbol, ListedType listedType) {
        this.assetPrice = assetPrice;
        this.symbol = symbol;
        this.listedType = listedType;
    }

    public void switchListed(ListedType listedType) {
        this.listedType = listedType;
    }

    public boolean isDelisted() {
        return listedType == ListedType.Delisted;
    }

    public AssetPrice calculateBuyPrice(AssetAmount amount) {
        if (getSymbol().getSymbolName().equals("KRW")) {
            return new AssetPrice(amount.getAmount());
        }
        return new AssetPrice(assetPrice.number().multiply(amount.number()).bigNumber());
    }

    public Assets toAssets() {
        return new Assets(
            assetPrice,
            symbol.getSymbolName(),
            symbol.getSymbolDisplayName(),
            isDelisted()
        );
    }

    public enum ListedType {
        Delisted, Listed
    }
}
