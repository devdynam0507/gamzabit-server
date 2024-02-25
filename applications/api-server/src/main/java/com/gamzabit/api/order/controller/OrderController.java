package com.gamzabit.api.order.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamzabit.api.infrastructure.common.Responses;
import com.gamzabit.api.infrastructure.security.dto.AuthenticatedUser;
import com.gamzabit.api.order.controller.dto.OrderCreateRequest;
import com.gamzabit.api.order.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Responses<Long> createOrder(AuthenticatedUser user, @RequestBody OrderCreateRequest orderCreateRequest) {
        Long createdOrderId = orderService.createOrder(user.getUser(), orderCreateRequest.toOrderCreate());

        return Responses.ok("주문이 성공적으로 생성되었습니다.", createdOrderId);
    }

    @DeleteMapping
    public void cancelOrder() {

    }
}
