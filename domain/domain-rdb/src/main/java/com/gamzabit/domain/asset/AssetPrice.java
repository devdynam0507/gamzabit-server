package com.gamzabit.domain.asset;

import java.math.BigDecimal;

import com.gamzabit.domain.common.DomainNumber;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class AssetPrice {

    @Column(precision = 30, scale = 10)
    private BigDecimal price;

    public AssetPrice(BigDecimal price) {
        this.price = price;
    }

    public DomainNumber number() {
        return new DomainNumber(price);
    }
}
