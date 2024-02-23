package com.gamzabit.api.infrastructure.common;

import lombok.Data;

@Data
public class AbstractCommonResponse<T> {

    private final String message;
    private final T data;
}
