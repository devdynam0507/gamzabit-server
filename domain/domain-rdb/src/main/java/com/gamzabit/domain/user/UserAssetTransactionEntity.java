package com.gamzabit.domain.user;

import java.math.BigDecimal;

import com.gamzabit.domain.common.EntityBase;
import com.gamzabit.domain.user.types.AssetTransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "user_asset_transaction")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class UserAssetTransactionEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 30, scale = 10)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private AssetTransactionType assetTransactionType;

    private String destinationAddress;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "user_asset_id")
    private UserAssetEntity userAsset;
}
