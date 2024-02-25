package com.gamzabit.api.order.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamzabit.api.infrastructure.security.dto.AuthenticatedUser;
import com.gamzabit.api.order.service.OrderService;
import com.gamzabit.api.user.service.vo.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void createOrder(AuthenticatedUser user) {
        log.info("user: {}", user);
    }

    @DeleteMapping
    public void cancelOrder() {

    }
}
