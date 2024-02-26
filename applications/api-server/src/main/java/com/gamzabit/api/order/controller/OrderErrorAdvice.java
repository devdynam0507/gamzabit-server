package com.gamzabit.api.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gamzabit.api.infrastructure.common.Responses;
import com.gamzabit.domain.asset.exception.AssetException;
import com.gamzabit.domain.order.exception.OrderException;

@RestControllerAdvice
public class OrderErrorAdvice {

    @ExceptionHandler(AssetException.class)
    public Responses<String> assetExceptionHandler(AssetException e) {
        return Responses.error(null, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(OrderException.class)
    public Responses<String> orderExceptionHandler(OrderException e) {
        return Responses.error(null, HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
