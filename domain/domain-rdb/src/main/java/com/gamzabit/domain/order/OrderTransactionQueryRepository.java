package com.gamzabit.domain.order;

import java.util.List;

import com.gamzabit.domain.order.vo.OrderHistory;

public interface OrderTransactionQueryRepository {

    List<OrderHistory> findAllByUserId(Long userId);
}
