package com.gamzabit.domain.redis.orderbook;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gamzabit.domain.redis.orderbook.dto.OrderBookOrderItem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderBookReader {

    private final static int DEFAULT_FETCH_SIZE = 5;
    private final static String sortKey = "assetBuyPrice";

    private final OrderBookRedisQueryRepository orderBookQueryRepository;
    private final OrderBookRedisRepository orderBookRepository;

    public List<OrderBookOrderItem> getMatchedSellOrders(Long assetId, BigDecimal assetBuyPrice) {
        return getMatchedSellOrders(assetId, assetBuyPrice, DEFAULT_FETCH_SIZE);
    }

    /**
     * 구매하려는 오더와 매치되는 판매 주문을 조회합니다.
     * 주문가 이하의 주문들을 모두 조회합니다.
     * */
    public List<OrderBookOrderItem> getMatchedSellOrders(Long assetId, BigDecimal assetBuyPrice, int fetchSize)  {
        PageRequest pageRequest =
            PageRequest.of(0, fetchSize, Sort.by(Direction.ASC, sortKey));
        List<Long> orderBookIds =
            orderBookQueryRepository.findByIdAndOrderTypeAndAssetBuyPriceLessThan(
                assetId, "Sell", assetBuyPrice, pageRequest
            )
            .stream()
            .map(OrderBookSortedSetKeyValue::orderId)
            .toList();
        List<OrderBook> orderBooks = orderBookRepository.findAllById(orderBookIds);

        return orderBooks.stream()
            .map(OrderBook::toOrderBookDto)
            .toList();
    }

    /**
     * 판매하려는 오더와 매치되는 구매 주문을 조회합니다.
     * 주문가 이상의 주문을 모두 조회합니다.
     * */
    public List<OrderBookOrderItem> getMatchedBuyOrders(Long assetId, BigDecimal assetBuyPrice) {
        return getMatchedSellOrders(assetId, assetBuyPrice, DEFAULT_FETCH_SIZE);
    }

    public List<OrderBookOrderItem> getMatchedBuyOrders(Long assetId, BigDecimal assetBuyPrice, int fetchSize) {
        PageRequest pageRequest =
            PageRequest.of(0, fetchSize, Sort.by(Direction.DESC, sortKey));
        List<Long> orderBookIds =
            orderBookQueryRepository.findByIdAndOrderTypeAndAssetBuyPriceGreaterThanEqual(
                assetId, "Buy", assetBuyPrice, pageRequest
            )
            .stream()
            .map(OrderBookSortedSetKeyValue::orderId)
            .toList();
        List<OrderBook> orderBooks = orderBookRepository.findAllById(orderBookIds);

        return orderBooks.stream()
            .map(OrderBook::toOrderBookDto)
            .toList();
    }
}
