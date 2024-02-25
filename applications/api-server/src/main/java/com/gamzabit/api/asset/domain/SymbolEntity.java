package com.gamzabit.api.asset.domain;

import com.gamzabit.api.common.domain.EntityBase;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
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

    private String symbolName;
    private Boolean isDelisted;
}
