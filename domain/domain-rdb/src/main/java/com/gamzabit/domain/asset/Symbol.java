package com.gamzabit.domain.asset;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Symbol {

    private String symbolName;
    private String symbolDisplayName;
}
