package com.gamzabit.api.infrastructure.common;

import lombok.Getter;

/**
 * 에러 상태를 표현하기 위한 AbstractCommonResponse의 구현체 입니다.
 *
 * @author skaeodud0507
 * */
@Getter
public class ErrorResponse<T, Error extends Enum<?>> extends AbstractCommonResponse<T> {

    private final Error errorCode;

    public ErrorResponse(String message, T data, Error errorCode) {
        super(message, data);
        this.errorCode = errorCode;
    }
}