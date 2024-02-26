package com.gamzabit.domain.order;

import java.math.BigDecimal;

import com.gamzabit.domain.common.EntityBase;

import jakarta.persistence.Column;
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

    @Builder
    public OrderEntity(
        Long userId,
        Long assetId,
        BigDecimal orderQuantity,
        Long orderPrice,
        OrderType orderType,
        OrderState orderState
    ) {
        this.userId = userId;
        this.assetId = assetId;
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
        this.orderType = orderType;
        this.orderState = orderState;
    }

    public void cancel() {
        this.orderState = OrderState.Cancel;
    }
}
