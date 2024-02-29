package com.gamzabit.domain.user;

import java.math.BigDecimal;

import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.common.DomainNumber;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class AssetAmount {

    @Column(precision = 30, scale = 10)
    private BigDecimal amount;

    public AssetAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public DomainNumber number() {
        return new DomainNumber(amount);
    }

    public void setAssetAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
