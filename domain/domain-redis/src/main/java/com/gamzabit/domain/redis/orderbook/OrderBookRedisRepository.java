package com.gamzabit.domain.redis.orderbook;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderBookRedisRepository {

    Optional<OrderBook> findById(Long id);

    List<OrderBook> findAllById(Collection<Long> ids);

    void save(OrderBook orderBook);

    void saveAll(Collection<OrderBook> orderBooks);

    void delete(OrderBook orderBook);

    void deleteById(Long id);
}
