package com.gamzabit.domain.order;

import java.math.BigDecimal;

import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.common.EntityBase;
import com.gamzabit.domain.order.vo.Order;
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

    @Embedded
    private AssetAmount orderQuantity;

    @Embedded
    private AssetPrice orderPrice;

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
        this.orderQuantity = new AssetAmount(orderQuantity);
        this.orderPrice = new AssetPrice(BigDecimal.valueOf(orderPrice));
        this.orderType = orderType;
        this.orderState = orderState;
    }

    public void cancel() {
        changeState(OrderState.Cancel);
    }

    public void pending() {
        changeState(OrderState.Pending);
    }

    public void available() {
        changeState(OrderState.Available);
    }

    public void invalid() {
        changeState(OrderState.Invalid);
    }

    public void conclude() {
        changeState(OrderState.Concluded);
    }

    public void changeState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Order toOrderDto() {
        return new Order(
            id,
            userId,
            assetId,
            orderQuantity,
            orderPrice,
            orderType,
            orderState,
            getCreatedAt()
        );
    }

    public OrderTransactionEntity createTransactionHistory() {
        return OrderTransactionEntity.builder()
            .orderId(id)
            .userId(userId)
            .concludedKrw(orderPrice)
            .concludedQuantity(orderQuantity)
            .orderState(orderState)
            .orderType(orderType)
            .build();
    }

    public enum OrderType { Buy, Sell, Cancel }

    public enum OrderState { Pending, Available, Concluded, Cancel, Invalid }
}
