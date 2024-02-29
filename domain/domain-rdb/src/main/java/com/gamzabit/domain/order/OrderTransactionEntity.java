package com.gamzabit.domain.order;

import java.math.BigDecimal;

import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.common.EntityBase;
import com.gamzabit.domain.user.AssetAmount;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @Builder
    public OrderTransactionEntity(Long userId, Long orderId, AssetAmount concludedQuantity, AssetPrice concludedKrw) {
        this.userId = userId;
        this.orderId = orderId;
        this.concludedQuantity = concludedQuantity;
        this.concludedKrw = concludedKrw;
    }
}
