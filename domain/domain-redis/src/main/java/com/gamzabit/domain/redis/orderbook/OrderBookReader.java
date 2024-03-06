package com.gamzabit.domain.redis.orderbook;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.redis.orderbook.dto.OrderBookOrderItem;
import com.gamzabit.domain.redis.orderbook.dto.OrderOperationBoard;

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
        List<Long> orderBookIds =
            orderBookQueryRepository.findByIdAndOrderTypeAndPrice(assetId, "Sell", assetBuyPrice).stream()
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
        return getMatchedBuyOrders(assetId, assetBuyPrice, DEFAULT_FETCH_SIZE);
    }

    public List<OrderBookOrderItem> getMatchedBuyOrders(Long assetId, BigDecimal assetBuyPrice, int fetchSize) {
        List<Long> orderBookIds =
            orderBookQueryRepository.findByIdAndOrderTypeAndPrice(assetId, "Buy", assetBuyPrice).stream()
                .map(OrderBookSortedSetKeyValue::orderId)
                .toList();
        List<OrderBook> orderBooks = orderBookRepository.findAllById(orderBookIds);

        return orderBooks.stream()
            .map(OrderBook::toOrderBookDto)
            .toList();
    }

    public OrderOperationBoard getOrderOperationBoard(Long id, BigDecimal price) {
        List<OrderOperationBoard.OrderOperationBoardItem> bids = new ArrayList<>();
        List<OrderOperationBoard.OrderOperationBoardItem> asks = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            int offset = i * 10;
            BigDecimal bidPrice = price.subtract(BigDecimal.valueOf(offset));
            BigDecimal askPrice = price.add(BigDecimal.valueOf(offset));

            List<Long> bidKeys =
                orderBookQueryRepository.findByIdAndOrderTypeAndPrice(id, "Buy", bidPrice)
                    .stream()
                    .map(OrderBookSortedSetKeyValue::orderId)
                    .toList();
            List<Long> askKeys =
                orderBookQueryRepository.findByIdAndOrderTypeAndPrice(id, "Sell", askPrice)
                    .stream()
                    .map(OrderBookSortedSetKeyValue::orderId)
                    .toList();

            BigDecimal totalBidAmount = bidKeys.stream()
                .map(orderBookRepository::findById)
                .map(bidOrder -> bidOrder.orElse(null))
                .filter(Objects::nonNull)
                .map(OrderBook::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal totalAskAmount = askKeys.stream()
                .map(orderBookRepository::findById)
                .map(askOrder -> askOrder.orElse(null))
                .filter(Objects::nonNull)
                .map(OrderBook::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            bids.add(OrderOperationBoard.create(bidPrice, totalBidAmount));
            asks.add(OrderOperationBoard.create(askPrice, totalAskAmount));
        }

        return new OrderOperationBoard(price, bids, asks);
    }
}
