package com.gamzabit.domain.order;

import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.common.EntityBase;
import com.gamzabit.domain.order.OrderEntity.OrderState;
import com.gamzabit.domain.order.OrderEntity.OrderType;
import com.gamzabit.domain.user.AssetAmount;

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

@Table(name = "order_transaction")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class OrderTransactionEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long orderId;

    private AssetAmount concludedQuantity;

    private AssetPrice concludedKrw;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Builder
    public OrderTransactionEntity(
        Long userId,
        Long orderId,
        AssetAmount concludedQuantity,
        AssetPrice concludedKrw,
        OrderState orderState,
        OrderType orderType
    ) {
        this.userId = userId;
        this.orderId = orderId;
        this.concludedQuantity = concludedQuantity;
        this.concludedKrw = concludedKrw;
        this.orderState = orderState;
        this.orderType = orderType;
    }
}
