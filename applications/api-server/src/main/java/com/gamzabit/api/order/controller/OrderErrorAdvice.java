package com.gamzabit.api.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gamzabit.api.asset.exception.AssetException;
import com.gamzabit.api.infrastructure.common.Responses;

@RestControllerAdvice
public class OrderErrorAdvice {

    @ExceptionHandler(AssetException.class)
    public Responses<String> assetExceptionHandler(AssetException e) {
        return Responses.error(null, HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
