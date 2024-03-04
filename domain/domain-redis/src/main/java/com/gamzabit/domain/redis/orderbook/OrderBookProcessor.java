package com.gamzabit.domain.redis.orderbook;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamzabit.domain.redis.orderbook.dto.OrderBookCreate;
import com.gamzabit.domain.redis.orderbook.dto.OrderBookOrderItem;
import com.gamzabit.domain.redis.orderbook.dto.OrderResults;
import com.gamzabit.domain.redis.orderbook.dto.OrderTransaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderBookProcessor {

    private final OrderBookRedisRepository orderBookRepository;
    private final OrderBookRedisQueryRepository orderBookRedisQueryRepository;
    private final OrderBookReader orderBookReader;

    public void deleteOrder(OrderBookCreate orderBookCreateDto) {
        orderBookRepository.deleteById(orderBookCreateDto.orderId());
    }

//    @Transactional
    public void saveOrder(OrderBookCreate orderBookCreateDto) {
        log.info("save order: {}", orderBookCreateDto);
        OrderBook orderBook = orderBookCreateDto.toOrderBook();

        orderBookRepository.save(orderBook);
        orderBookRedisQueryRepository.save(orderBook);
    }

    public void saveAllOrders(List<OrderBookCreate> orderBooks) {
        List<OrderBook> insertableOrderBooks = orderBooks.stream()
            .map(OrderBookCreate::toOrderBook)
            .toList();
        orderBookRepository.saveAll(insertableOrderBooks);
        insertableOrderBooks.forEach(orderBookRedisQueryRepository::save);
    }

    public OrderResults buy(OrderBookCreate buyOrderCreate) {
        List<OrderBookOrderItem> sellOrders =
            orderBookReader.getMatchedSellOrders(buyOrderCreate.id(), buyOrderCreate.assetBuyPrice());
        if (sellOrders.isEmpty()) {
            return OrderResults.empty();
        }
        List<OrderBookOrderItem> branchedOrderBooks = new ArrayList<>();
        List<OrderTransaction> transactions = new ArrayList<>();
        BigDecimal buyOrderAmount = buyOrderCreate.amount();
        for (OrderBookOrderItem sellOrder : sellOrders) {
            BigDecimal sellOrderAmount = sellOrder.amount();
            Long concludePrice = buyOrderAmount
                .multiply(sellOrder.assetBuyPrice())
                .longValue();

            // 구매 수량이 불러온 판매 수량보다 더 클 경우
            if (buyOrderAmount.compareTo(sellOrderAmount) > 0) {
                // 구매 수량 - 판매 수량 => 남은 구매 수량
                BigDecimal subtractedQuantity = buyOrderAmount.subtract(sellOrderAmount);
                OrderBookCreate remainBuyOrder = buyOrderCreate.branchNewQuantity(subtractedQuantity);

                branchedOrderBooks.add(remainBuyOrder.toOrderBookDto());
            }
            // 구매 수량이 불러온 판매 수량보다 더 작거나 같을 경우
            // 구매: 10 판매: 20
            else {
                BigDecimal subtractedQuantity = sellOrderAmount.subtract(buyOrderAmount);
                // 판매 수량이 구매 주문을 처리하고 남은 수량이 0 보다 크다면
                if (subtractedQuantity.compareTo(BigDecimal.ZERO) > 0) {
                    // 판매 수량을 쪼개고 남은 쪼개진 주문 리스트에 추가한다.
                    OrderBookCreate remainSellOrder = sellOrder.toOrderBookCreationDto()
                        .branchNewQuantity(subtractedQuantity);

                    branchedOrderBooks.add(remainSellOrder.toOrderBookDto());
                }
            }
            deleteOrder(sellOrder.toOrderBookCreationDto());
            OrderTransaction sellOrderTransaction =
                OrderTransaction.createTransaction(sellOrder, buyOrderAmount, concludePrice);
            OrderTransaction buyOrderTransaction =
                OrderTransaction.createTransaction(buyOrderCreate, buyOrderAmount, concludePrice);
            transactions.add(sellOrderTransaction);
            transactions.add(buyOrderTransaction);
        }

        return new OrderResults(branchedOrderBooks, transactions);
    }

    public OrderResults sell(OrderBookCreate sellOrderCreate) {
        List<OrderBookOrderItem> buyOrders =
            orderBookReader.getMatchedBuyOrders(sellOrderCreate.id(), sellOrderCreate.assetBuyPrice());
        if (buyOrders.isEmpty()) {
            return OrderResults.empty();
        }
        List<OrderBookOrderItem> branchedOrderBooks = new ArrayList<>();
        List<OrderTransaction> transactions = new ArrayList<>();
        BigDecimal sellOrderAmount = sellOrderCreate.amount();
        OrderBookOrderItem sellOrder = sellOrderCreate.toOrderBookDto();
        for (OrderBookOrderItem buyOrder : buyOrders) {
            BigDecimal buyOrderAmount = buyOrder.amount();
            OrderBookCreate buyOrderDto = buyOrder.toOrderBookCreationDto();
            // 거래에 대한 체결가를 계산한다 -> 판매 수량 * 구매한 자산 가격
            Long concludePrice = sellOrderAmount
                .multiply(sellOrder.assetBuyPrice())
                .longValue();
            // 구매 수량이 판매 수량보다 클 경우
            if (buyOrderAmount.compareTo(sellOrderAmount) > 0) {
                BigDecimal subtractedSellQuantity = buyOrderAmount.subtract(sellOrderAmount);
                // 구매 주문의 경우 두 주문을 체결하고 아직 구매하지 못한 수량이 있으므로 주문을 한개 더 생성한다.
                OrderBookCreate remainBuyOrder = buyOrderDto.branchNewQuantity(subtractedSellQuantity);

                branchedOrderBooks.add(remainBuyOrder.toOrderBookDto());
            }
            // 판매 수량이 구매 수량보다 크거나 같은 경우
            else {
                BigDecimal subtractedSellQuantity = sellOrderAmount.subtract(buyOrderAmount);
                // 모두 판매되지 못했다면
                if (subtractedSellQuantity.compareTo(BigDecimal.ZERO) > 0) {
                    // 판매 주문을 쪼개고 거래내역을 추가한다.
                    OrderBookCreate remainSellOrder = sellOrderCreate.branchNewQuantity(subtractedSellQuantity);
                    // 쪼개진 판매 주문의 경우 아직 레디스에 저장되지 않았기 때문에 그냥 남은 주문에만 추가한다.
                    branchedOrderBooks.add(remainSellOrder.toOrderBookDto());
                }
            }
            deleteOrder(buyOrderDto);
            OrderTransaction buyTransaction =
                OrderTransaction.createTransaction(buyOrder, sellOrderAmount, concludePrice);
            OrderTransaction sellTransaction =
                OrderTransaction.createTransaction(sellOrder, sellOrderAmount, concludePrice);
            transactions.add(buyTransaction);
            transactions.add(sellTransaction);
        }

        return new OrderResults(branchedOrderBooks, transactions);
    }
}
