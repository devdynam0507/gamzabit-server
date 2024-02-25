package com.gamzabit.api.user.domain;

import java.math.BigDecimal;

import com.gamzabit.api.common.domain.EntityBase;

import jakarta.persistence.Column;
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

    private String assetName;

    private String assetDisplayName;

    @Column(precision = 30, scale = 10)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private UserEntity user;

    @Builder
    public UserAssetEntity(String assetName, String assetDisplayName, BigDecimal amount, UserEntity user) {
        this.assetName = assetName;
        this.assetDisplayName = assetDisplayName;
        this.amount = amount;
        this.user = user;
    }
}
