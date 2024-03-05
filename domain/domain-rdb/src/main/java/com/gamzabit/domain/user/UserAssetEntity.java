package com.gamzabit.domain.user;

import java.math.BigDecimal;

import com.gamzabit.domain.asset.AssetEntity;
import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.asset.DefaultAssetTypes;
import com.gamzabit.domain.common.DomainNumber;
import com.gamzabit.domain.common.EntityBase;
import com.gamzabit.domain.user.vo.UserAsset;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "user_asset")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class UserAssetEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AssetAmount amount;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private AssetEntity asset;

    @Builder
    public UserAssetEntity(AssetAmount amount, Long userId, AssetEntity asset) {
        this.amount = amount;
        this.userId = userId;
        this.asset = asset;
    }

    public AssetPrice calculateTotalKrwPrice() {
        if (isKrw()) {
            return new AssetPrice(amount.getAmount());
        }
        DomainNumber price = asset.getAssetPrice()
            .number()
            .multiply(amount.number());
        return new AssetPrice(price.bigNumber());
    }

    public boolean isKrw() {
        return asset.getSymbol().getSymbolName().equals(DefaultAssetTypes.KRW.name());
    }

    public void deposit(AssetPrice assetPrice) {
        amount.setAssetAmount(
            amount.number().add(assetPrice.number()).bigNumber()
        );
    }

    public void withdraw(AssetPrice assetPrice) {
        // 현재 자산이 가격보다 많지 않은 경우
        System.out.println(amount.getAmount());
        System.out.println(assetPrice.getPrice());
        if (!amount.number().isGreaterOrEqualThan(assetPrice.number())) {
            throw new RuntimeException("자산이 부족합니다.");
        }
        BigDecimal subtractedAmount = amount.getAmount().subtract(assetPrice.getPrice());
        amount.setAssetAmount(subtractedAmount);
    }

    public UserAssetTransactionEntity createAssetHistory() {
        return null;
    }

    public UserAsset toUserAsset() {
        return new UserAsset(
            amount,
            asset.toAssets()
        );
    }
}
