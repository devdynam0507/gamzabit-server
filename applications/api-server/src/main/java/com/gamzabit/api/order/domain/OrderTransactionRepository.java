package com.gamzabit.api.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTransactionRepository extends JpaRepository<OrderTransactionEntity, Long> {
}
