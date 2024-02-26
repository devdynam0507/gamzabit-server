package com.gamzabit.domain.user;

import java.math.BigDecimal;

import com.gamzabit.api.asset.domain.SymbolEntity;
import com.gamzabit.domain.common.EntityBase;

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

    @Column(precision = 30, scale = 10)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "symbol_id")
    private SymbolEntity symbol;

    @Builder
    public UserAssetEntity(BigDecimal amount, UserEntity user, SymbolEntity symbol) {
        this.amount = amount;
        this.user = user;
        this.symbol = symbol;
    }

    public static UserAssetEntity create(UserEntity user, SymbolEntity symbol, BigDecimal initialAmount) {
        return UserAssetEntity.builder()
            .user(user)
            .symbol(symbol)
            .amount(initialAmount)
            .build();
    }
}
