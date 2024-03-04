package com.gamzabit.domain.redis.orderbook;

import org.springframework.stereotype.Service;

@Service
public class OrderBookKeyResolver {

    public String createSortedSetKey(String orderType, Long id) {
        return orderType + ":" + id;
    }
}
