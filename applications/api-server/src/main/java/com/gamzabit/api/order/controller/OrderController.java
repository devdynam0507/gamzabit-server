package com.gamzabit.api.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamzabit.api.infrastructure.common.Responses;
import com.gamzabit.api.infrastructure.security.dto.AuthenticatedUser;
import com.gamzabit.api.order.controller.dto.OrderCreateRequest;
import com.gamzabit.api.order.service.OrderCancelService;
import com.gamzabit.api.order.service.OrderCreateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderCreateService orderCreator;
    private final OrderCancelService orderCanceler;

    @PostMapping
    public Responses<Long> createOrder(AuthenticatedUser user, @RequestBody OrderCreateRequest orderCreateRequest) {
        Long createdOrderId = orderCreator.createOrder(user.getUser(), orderCreateRequest.toOrderCreate());

        return Responses.created("주문이 성공적으로 생성되었습니다.", createdOrderId);
    }

    @DeleteMapping("/{orderId}")
    public Responses<Void> cancelOrder(AuthenticatedUser user, @PathVariable("orderId") Long orderId) {
        orderCanceler.cancelOrder(user.getUser().id(), orderId);

        return Responses.onlyMessage(HttpStatus.OK, "주문을 취소하였습니다.");
    }
}
