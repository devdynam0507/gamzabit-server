package com.gamzabit.api.infrastructure.common;

/**
 * 순수하게 메시지와 응답 데이터만을 표현하기 위한 AbstractCommonResponse의 구현체 입니다.
 *
 * @author skaeodud0507
 * */
public class BodyMessageResponse<T> extends AbstractCommonResponse<T>{

    public BodyMessageResponse(final String message, final T data) {
        super(message, data);
    }

    public static <T> BodyMessageResponse<T> of(final String message, final T data) {
        return new BodyMessageResponse<>(message, data);
    }
}