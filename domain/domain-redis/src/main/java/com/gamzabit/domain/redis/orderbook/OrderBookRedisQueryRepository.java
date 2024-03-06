package com.gamzabit.domain.redis.orderbook;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;

public interface OrderBookRedisQueryRepository {

    void save(OrderBook orderBook);

    void delete(OrderBook orderBook);

    List<OrderBookSortedSetKeyValue> findByIdAndOrderTypeAndAssetBuyPriceGreaterThanEqual(
        Long id, String orderType, BigDecimal assetBuyPrice, Pageable pageable
    );

    List<OrderBookSortedSetKeyValue> findByIdAndOrderTypeAndAssetBuyPriceLessThan(
        Long id, String orderType, BigDecimal assetBuyPrice, Pageable pageable
    );

    List<OrderBookSortedSetKeyValue> findByIdAndOrderTypeAndPrice(Long id, String orderType, BigDecimal price);
}
