package com.gamzabit.domain.asset;

import java.math.BigDecimal;

import com.gamzabit.domain.common.EntityBase;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "symbol")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class SymbolEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal currentValue;
    private String symbolName;
    private String symbolDisplayName;
    private Boolean delisted;

    @Builder
    public SymbolEntity(BigDecimal currentValue, String symbolName, String symbolDisplayName, Boolean delisted) {
        this.currentValue = currentValue;
        this.symbolName = symbolName;
        this.symbolDisplayName = symbolDisplayName;
        this.delisted = delisted;
    }
}
