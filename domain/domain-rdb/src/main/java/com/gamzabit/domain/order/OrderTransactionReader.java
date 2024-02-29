package com.gamzabit.domain.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.order.vo.OrderHistory;
import com.gamzabit.domain.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderTransactionReader {

    private final OrderTransactionQueryRepository orderTransactionRepository;

    public List<OrderHistory> getOrderHistory(User user) {
        return orderTransactionRepository.findAllByUserId(user.id().longValue());
    }
}
