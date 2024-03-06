package com.gamzabit.domain.redis.orderbook;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderBookRedisRepositoryImpl implements OrderBookRedisRepository {

    private final String HASH_KEY = "order_book";
    private final RedisTemplate<String, OrderBook> redisTemplate;

    @Override
    public Optional<OrderBook> findById(Long id) {
        OrderBook orderBook = redisTemplate.<Long, OrderBook>opsForHash().get(HASH_KEY, id);

        return Optional.ofNullable(orderBook);
    }

    @Override
    public List<OrderBook> findAllById(Collection<Long> ids) {
        return redisTemplate.<Long, OrderBook>opsForHash().multiGet(
            HASH_KEY, ids
        );
    }

    @Override
    public void save(OrderBook orderBook) {
        redisTemplate.opsForHash().put(HASH_KEY, orderBook.getOrderId(), orderBook);
    }

    @Override
    public void saveAll(Collection<OrderBook> orderBooks) {
        Map<Long, OrderBook> maps = orderBooks.stream()
            .collect(Collectors.toMap(OrderBook::getOrderId, v -> v));
        redisTemplate.opsForHash().putAll(HASH_KEY, maps);
    }

    @Override
    public void delete(OrderBook orderBook) {
        deleteById(orderBook.getOrderId());
    }

    @Override
    public void deleteById(Long id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id);
    }
}
