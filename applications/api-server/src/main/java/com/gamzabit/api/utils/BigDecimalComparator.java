package com.gamzabit.api.utils;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;

public class BigDecimalComparator {

    private final BigDecimal bigNumber;

    BigDecimalComparator(BigDecimal bigNumber) {
        requireNonNull(bigNumber, "bigNumber");

        this.bigNumber = bigNumber;
    }

    public static BigDecimalComparator of(BigDecimal bigNumber) {
        return new BigDecimalComparator(bigNumber);
    }

    public boolean equalsOrBiggerThen(BigDecimal target) {
        requireNonNull(target, "target");

        return bigNumber.compareTo(target) >= 0;
    }

    public boolean equalsOrLowerThen(BigDecimal target) {
        requireNonNull(target, "target");

        return bigNumber.compareTo(target) <= 0;
    }

    public boolean isBiggerThen(BigDecimal target) {
        requireNonNull(target, "target");

        return bigNumber.compareTo(target) > 0;
    }

    public boolean isLowerThen(BigDecimal target) {
        requireNonNull(target, "target");

        return bigNumber.compareTo(target) < 0;
    }

    @Override
    public boolean equals(Object target) {
        requireNonNull(target, "target");
        if (!(target instanceof BigDecimal)) {
            return false;
        }

        return bigNumber.compareTo((BigDecimal) target) == 0;
    }
}
