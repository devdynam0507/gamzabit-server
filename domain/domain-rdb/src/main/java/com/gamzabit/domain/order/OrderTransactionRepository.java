package com.gamzabit.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTransactionRepository extends JpaRepository<OrderTransactionEntity, Long> {
}
