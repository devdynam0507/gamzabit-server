package com.gamzabit.domain.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record DomainNumber(
    BigDecimal bigNumber
) {

    public DomainNumber(BigDecimal bigNumber) {
        this.bigNumber = bigNumber;
    }

    public DomainNumber() {
        this(BigDecimal.ZERO);
    }

    public DomainNumber subtract(DomainNumber target) {
        BigDecimal subtracted = bigNumber.subtract(target.bigNumber);

        return new DomainNumber(subtracted);
    }

    public DomainNumber add(DomainNumber target) {
        BigDecimal added = bigNumber.add(target.bigNumber);

        return new DomainNumber(added);
    }

    public DomainNumber multiply(DomainNumber target) {
        BigDecimal multiplied = bigNumber.multiply(target.bigNumber);

        return new DomainNumber(multiplied);
    }

    public DomainNumber divide(DomainNumber target) {
        BigDecimal divided = bigNumber.divide(target.bigNumber, RoundingMode.UNNECESSARY);

        return new DomainNumber(divided);
    }

    public boolean isGreaterThan(DomainNumber target) {
        return isGreaterThan(target.bigNumber);
    }

    public boolean isGreaterThan(BigDecimal bigDecimal) {
        return bigNumber.compareTo(bigDecimal) > 0;
    }

    public boolean isGreaterOrEqualThan(DomainNumber target) {
        return isGreaterOrEqualThan(target.bigNumber);
    }

    public boolean isGreaterOrEqualThan(BigDecimal bigDecimal) {
        return bigNumber.compareTo(bigDecimal) >= 0;
    }

    public boolean isLessThan(DomainNumber target) {
        return isLessThan(target.bigNumber);
    }

    public boolean isLessThan(BigDecimal bigDecimal) {
        return bigNumber.compareTo(bigDecimal) < 0;
    }

    public boolean isLessOrEqualThan(DomainNumber target) {
        return isLessOrEqualThan(target.bigNumber);
    }

    public boolean isLessOrEqualThan(BigDecimal bigDecimal) {
        return bigNumber.compareTo(bigDecimal) <= 0;
    }

    @Override
    public boolean equals(Object target) {
        if (!(target instanceof DomainNumber domainNumber)) {
            return false;
        }

        return bigNumber.compareTo(domainNumber.bigNumber) == 0;
    }
}
