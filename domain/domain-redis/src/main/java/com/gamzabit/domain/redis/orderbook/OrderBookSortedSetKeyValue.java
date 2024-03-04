package com.gamzabit.domain.redis.orderbook;

public record OrderBookSortedSetKeyValue(
   long orderId,
   long userId
) {}
