package com.gamzabit.domain.order;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamzabit.domain.asset.AssetEntity;
import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.asset.AssetRepository;
import com.gamzabit.domain.asset.DefaultAssetTypes;
import com.gamzabit.domain.asset.exception.AssetNotFoundException;
import com.gamzabit.domain.order.OrderEntity.OrderType;
import com.gamzabit.domain.order.exception.OrderNotFoundException;
import com.gamzabit.domain.order.vo.Order;
import com.gamzabit.domain.order.vo.OrderTransaction;
import com.gamzabit.domain.user.UserAssetFreezeProcessor;
import com.gamzabit.domain.user.UserAssetProcessor;
import com.gamzabit.domain.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderProcessor {

    private final OrderRepository orderRepository;
    private final OrderTransactionRepository orderTransactionRepository;
    private final UserAssetProcessor userAssetProcessor;
    private final AssetRepository assetRepository;

    @Transactional
    public Long buy() {
        return -1L;
    }

    @Transactional
    public Long sell() {
        return -1L;
    }

    @Transactional
    public void handleConcludeOrder(List<OrderTransaction> transactions) {
        transactions.sort(Comparator.comparing(OrderTransaction::concludedTime).reversed());
        List<OrderEntity> orderEntities = transactions.stream()
            .map(transaction -> orderRepository.findByIdAndUserId(transaction.orderId(), transaction.userId()))
            .map(orderEntity -> orderEntity.orElseThrow(() -> new OrderNotFoundException(0L)))
            .toList();
        // 주문 상태를 Concluded로 바꾼다.
        List<OrderTransactionEntity> orderTransactionEntities = orderEntities.stream()
            .peek(OrderEntity::conclude)
            .map(OrderEntity::createTransactionHistory)
            .toList();
        transactions.forEach(transaction -> {
            switch (transaction.orderType()) {
                case "Buy" -> handleBuyOrder(transaction);
                case "Sell" -> handleSellOrder(transaction);
                default -> throw new UnsupportedOperationException("지원되지 않는 거래 체결 타입 입니다.");
            }
        });
        OrderTransaction lastConcludeOrder = transactions.get(transactions.size() - 1);
        Long assetId = lastConcludeOrder.assetId();
        AssetEntity assetEntity = assetRepository.findById(lastConcludeOrder.assetId())
            .orElseThrow(
                () -> new AssetNotFoundException(assetId + "거래소 자산을 찾을 수 없습니다.", String.valueOf(assetId))
            );
        assetEntity.changePrice(new AssetPrice(BigDecimal.valueOf(lastConcludeOrder.concludedPrice())));

        // 관련 거래 내역을 모두 저장한다.
        orderTransactionRepository.saveAll(orderTransactionEntities);
    }

    void handleBuyOrder(OrderTransaction orderTransaction) {
        User user = User.createOnlyId(orderTransaction.userId());

        userAssetProcessor.depositTo(
            user, new AssetPrice(orderTransaction.concludedAmount()), orderTransaction.assetId());
    }

    void handleSellOrder(OrderTransaction orderTransaction) {
        User user = User.createOnlyId(orderTransaction.userId());
        AssetPrice concludedPrice =
            new AssetPrice(BigDecimal.valueOf(orderTransaction.concludedPrice()));

        userAssetProcessor.depositTo(user, concludedPrice, DefaultAssetTypes.KRW.name());
    }
}
