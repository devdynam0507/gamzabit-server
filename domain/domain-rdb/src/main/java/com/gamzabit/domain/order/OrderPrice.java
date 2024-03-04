package com.gamzabit.domain.order;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderPrice {

    @Column(precision = 30, scale = 10)
    private BigDecimal orderPriceKrw;

    @Column(precision = 30, scale = 10)
    private BigDecimal assetPriceKrw;
}
