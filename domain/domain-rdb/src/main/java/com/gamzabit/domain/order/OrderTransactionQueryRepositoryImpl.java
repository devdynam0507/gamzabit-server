package com.gamzabit.domain.order;

import static com.gamzabit.domain.order.QOrderTransactionEntity.*;
import static com.gamzabit.domain.order.QOrderEntity.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gamzabit.domain.order.vo.Order;
import com.gamzabit.domain.order.vo.OrderHistory;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderTransactionQueryRepositoryImpl implements OrderTransactionQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<OrderHistory> findAllByUserId(Long userId) {
        return queryFactory
            .select(Projections.constructor(
                OrderHistory.class,
                Projections.constructor(
                    Order.class,
                    orderEntity.id,
                    null,
                    null,
                    orderEntity.orderQuantity,
                    orderEntity.orderPrice,
                    orderEntity.orderType,
                    orderEntity.orderState,
                    orderEntity.createdAt
                ),
                orderTransactionEntity.concludedQuantity,
                orderTransactionEntity.concludedKrw
            ))
            .from(orderTransactionEntity)
            .where(orderTransactionEntity.userId.eq(userId))
            .fetch();
    }
}
