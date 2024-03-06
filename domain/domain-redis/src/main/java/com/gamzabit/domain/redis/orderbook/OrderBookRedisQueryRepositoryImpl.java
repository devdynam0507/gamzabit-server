package com.gamzabit.domain.redis.orderbook;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderBookRedisQueryRepositoryImpl implements OrderBookRedisQueryRepository {

    private final StringRedisTemplate redisTemplate;
    private final OrderBookKeyResolver orderBookKeyResolver;

    @Override
    public void save(OrderBook orderBook) {
        String key = orderBookKeyResolver.createSortedSetKey(orderBook.getOrderType(), orderBook.getId());
        String value = orderBook.getOrderId() + ":" + orderBook.getUserId();
        double score = orderBook.getAssetBuyPrice().doubleValue();

        redisTemplate.opsForZSet().add(key, value, score);
    }

    @Override
    public void delete(OrderBook orderBook) {
        String key = orderBookKeyResolver.createSortedSetKey(orderBook.getOrderType(), orderBook.getId());
        String value = orderBook.getOrderId() + ":" + orderBook.getUserId();

        redisTemplate.opsForZSet().remove(key, value);
    }

    @Override
    public List<OrderBookSortedSetKeyValue> findByIdAndOrderTypeAndAssetBuyPriceGreaterThanEqual(
        Long id, String orderType, BigDecimal assetBuyPrice, Pageable pageable
    ) {
        String key = orderBookKeyResolver.createSortedSetKey(orderType, id);
        Set<String> orderBooks = redisTemplate.opsForZSet()
            .rangeByScore(key, assetBuyPrice.doubleValue(), Integer.MAX_VALUE);

        return Objects.requireNonNull(orderBooks)
            .stream()
            .limit(pageable.getPageSize())
            .map(value -> {
                String[] split = value.split(":");
                long orderId = Long.parseLong(split[0]);
                long userId = Long.parseLong(split[1]);

                return new OrderBookSortedSetKeyValue(orderId, userId);
            })
            .toList();
    }

    @Override
    public List<OrderBookSortedSetKeyValue> findByIdAndOrderTypeAndAssetBuyPriceLessThan(
        Long id, String orderType, BigDecimal assetBuyPrice, Pageable pageable
    ) {
        String key = orderBookKeyResolver.createSortedSetKey(orderType, id);
        Set<String> orderBooks = redisTemplate.opsForZSet()
            .reverseRangeByScore(key, Integer.MIN_VALUE, assetBuyPrice.doubleValue());

        return Objects.requireNonNull(orderBooks)
            .stream()
            .limit(pageable.getPageSize())
            .map(value -> {
                String[] split = value.split(":");
                long orderId = Long.parseLong(split[0]);
                long userId = Long.parseLong(split[1]);

                return new OrderBookSortedSetKeyValue(orderId, userId);
            })
            .toList();
    }

    @Override
    public List<OrderBookSortedSetKeyValue> findByIdAndOrderTypeAndPrice(Long id, String orderType, BigDecimal price) {
        String key = orderBookKeyResolver.createSortedSetKey(orderType, id);
        long priceLong = price.longValue();
        Set<String> orderBooks = redisTemplate.opsForZSet().rangeByScore(key, priceLong, priceLong);

        return Objects.requireNonNull(orderBooks).stream()
            .map(value -> {
                String[] split = value.split(":");
                long orderId = Long.parseLong(split[0]);
                long userId = Long.parseLong(split[1]);

                return new OrderBookSortedSetKeyValue(orderId, userId);
            })
            .toList();
    }
}
