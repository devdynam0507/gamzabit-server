package com.gamzabit.api.infrastructure.common;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

/**
 * ResponseEntity를 상속받은 공통 응답 객체입니다.
 * AbstractCommonResponse를 구현한 클래스를 래핑하여 Http Status 코드와 응답 객체를 생성합니다.
 *
 * @see ResponseEntity
 * @author skaeodud0507
 * */
@Getter
public class Responses<T> extends ResponseEntity<AbstractCommonResponse<T>> {

    Responses(final HttpStatus httpStatus, final AbstractCommonResponse<T> response) {
        super(response, httpStatus);
    }

    /**
     * 커스텀 메시지와 데이터를 공통 응답 객체로 만들어서 반환합니다.
     *
     * @param status HTTP 응답 상태 코드입니다.
     * @param message 현재 응답에 대한 커스텀 메시지 입니다.
     * @param data 현재 응답에 대한 데이터 입니다.
     *
     * @return 메시지와 데이터가 포함된 공통 응답 객체
     * */
    public static <T> Responses<T> withMessage(final HttpStatus status, final String message, final T data) {
        return new Responses<>(status, new BodyMessageResponse<>(message, data));
    }

    public static <T> Responses<List<T>> ofPagedResponse(final AbstractCommonResponse<List<T>> response) {
        return new Responses<>(HttpStatus.OK, response);
    }

    public static <T> Responses<List<T>> ofPagedResponse(
        final String message,
        final List<T> data,
        final int totalPage,
        final int currentPage,
        final boolean hasNext
    ) {
        final AbstractCommonResponse<List<T>> response = new CommonPagedResponse<>(
            message,
            data,
            totalPage,
            currentPage,
            hasNext
        );
        return ofPagedResponse(response);
    }

    /**
     * 커스텀 메시지만 내려주는 경우 사용합니다.
     *
     * @param status HTTP 응답 상태 코드입니다.
     * @param message 현재 응답에 대한 커스텀 메시지 입니다.
     *
     * @return 메시지만 포함된 공통 응답 객체
     * */
    public static <T> Responses<T> onlyMessage(final HttpStatus status, final String message) {
        return new Responses<>(status, new BodyMessageResponse<>(message, null));
    }

    /**
     * 200 응답코드와 Body 데이터를 같이 내려줍니다.
     *
     * @param message 현재 응답에 대한 커스텀 메시지 입니다.
     * @param data 현재 응답에 대한 데이터 입니다.
     *
     * @return 200 응답코드와 메시지, 데이터가 포함된 공통 응답 객체
     * */
    public static <T> Responses<T> ok(final String message, T data) {
        return new Responses<>(HttpStatus.OK, new BodyMessageResponse<>(message, data));
    }

    /**
     * 201 응답코드와 Body 데이터를 같이 내려줍니다.
     *
     * @param message 현재 응답에 대한 커스텀 메시지 입니다.
     * @param data 현재 응답에 대한 데이터 입니다.
     *
     * @return 201 응답코드와 메시지, 데이터가 포함된 공통 응답 객체
     * */
    public static <T> Responses<T> created(final String message, T data) {
        return new Responses<>(HttpStatus.CREATED, new BodyMessageResponse<>(message, data));
    }

    /**
     * 400 Bad Request를 메시지와 데이터를 함께 내려주는 경우 사용합니다.
     *
     * @param message 현재 응답에 대한 커스텀 메시지 입니다.
     *
     * @return 400 응답코드와 메시지, 데이터가 포함된 공통 응답 객체
     * */
    public static <T> Responses<T> badRequest(final String message, final T data) {
        return new Responses<>(HttpStatus.BAD_REQUEST, new BodyMessageResponse<>(message, data));
    }

    /**
     * 클라이언트에게 보다 정확한 에러 사유를 내려줄 때 사용합니다.
     *
     * @param errorCode 커스텀 에러코드 입니다.
     * @param status HTTP 상태코드 입니다.
     * @param message 현재 응답에 대한 커스텀 메시지 입니다.
     * @param data 응답에 포함할 데이터 입니다.
     *
     * @return 상세한 에러코드, 메시지, 데이터가 포함된 공통 에러 응답 객체
     * */
    public static <T, Error extends Enum<?>> Responses<T> error(
            final Error errorCode, final HttpStatus status, final String message, final T data) {
        return new Responses<>(status, new ErrorResponse<>(message, data, errorCode));
    }

    /**
     * 클라이언트에게 보다 정확한 에러 사유를 내려줄 때 사용합니다.
     * 이 메서드에서는 응답 데이터를 포함하지 않습니다.
     *
     * @param errorCode 커스텀 에러코드 입니다.
     * @param status HTTP 상태코드 입니다.
     * @param message 현재 응답에 대한 커스텀 메시지 입니다.
     *
     * @return 상세한 에러코드, 메시지, 데이터가 포함된 공통 에러 응답 객체
     * */
    public static <T, Error extends Enum<?>> Responses<T> error(
            final Error errorCode, final HttpStatus status, final String message) {
        return new Responses<>(status, new ErrorResponse<>(message, null, errorCode));
    }
}