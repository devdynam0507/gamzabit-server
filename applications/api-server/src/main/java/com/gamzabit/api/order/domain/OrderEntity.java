package com.gamzabit.api.order.domain;

import java.math.BigDecimal;

import com.gamzabit.api.common.domain.EntityBase;
import com.gamzabit.api.order.domain.types.OrderState;
import com.gamzabit.api.order.domain.types.OrderType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class OrderEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long assetId;

    @Column(precision = 30, scale = 10)
    private BigDecimal orderQuantity;

    private Long orderPrice;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;
}