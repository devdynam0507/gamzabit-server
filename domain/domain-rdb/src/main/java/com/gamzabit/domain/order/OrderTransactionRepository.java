package com.gamzabit.domain.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamzabit.domain.order.vo.Order;

public interface OrderTransactionRepository extends JpaRepository<OrderTransactionEntity, Long> {
}
