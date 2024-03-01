package com.gamzabit.domain.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.order.exception.OrderNotFoundException;
import com.gamzabit.domain.order.vo.Order;
import com.gamzabit.domain.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderReader {

    private final OrderRepository orderRepository;

    public Order getUserOrder(User user, Long orderId) {
        return orderRepository.findByIdAndUserId(orderId, user.id())
            .orElseThrow(() -> new OrderNotFoundException(orderId))
            .toOrderDto();
    }

    public List<Order> getUserOrders(User user) {
        List<OrderEntity> orders = orderRepository.findByUserId(user.id());

        return toOrders(orders);
    }

    public List<Order> getOrdersBy(OrderEntity.OrderState orderState) {
        List<OrderEntity> orders = orderRepository.findByOrderState(orderState);

        return toOrders(orders);
    }

    public List<Order> getOrdersBy(OrderEntity.OrderType orderType) {
        List<OrderEntity> orders = orderRepository.findByOrderType(orderType);

        return toOrders(orders);
    }

    public List<Order> getOrdersBy(OrderEntity.OrderType orderType, OrderEntity.OrderState orderState) {
        List<OrderEntity> orders = orderRepository.findByOrderTypeAndOrderState(orderType, orderState);

        return toOrders(orders);
    }

    List<Order> toOrders(List<OrderEntity> orders) {
        return orders.stream()
            .map(OrderEntity::toOrderDto)
            .toList();
    }
}
