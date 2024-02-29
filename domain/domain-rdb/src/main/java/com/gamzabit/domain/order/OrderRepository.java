package com.gamzabit.domain.order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByIdAndUserId(Long id, Long userId);

    List<OrderEntity> findByUserId(Long userId);

    List<OrderEntity> findByOrderState(OrderEntity.OrderState orderState);

    List<OrderEntity> findByOrderType(OrderEntity.OrderType orderType);

    List<OrderEntity> findByOrderTypeAndOrderState(OrderEntity.OrderType orderType, OrderEntity.OrderState orderState);
}
