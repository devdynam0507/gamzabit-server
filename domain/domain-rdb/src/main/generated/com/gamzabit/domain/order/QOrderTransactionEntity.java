package com.gamzabit.domain.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderTransactionEntity is a Querydsl query type for OrderTransactionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderTransactionEntity extends EntityPathBase<OrderTransactionEntity> {

    private static final long serialVersionUID = 1640727247L;

    public static final QOrderTransactionEntity orderTransactionEntity = new QOrderTransactionEntity("orderTransactionEntity");

    public final com.gamzabit.domain.common.QEntityBase _super = new com.gamzabit.domain.common.QEntityBase(this);

    public final NumberPath<Long> concludedKrw = createNumber("concludedKrw", Long.class);

    public final NumberPath<java.math.BigDecimal> concludedQuantity = createNumber("concludedQuantity", java.math.BigDecimal.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QOrderTransactionEntity(String variable) {
        super(OrderTransactionEntity.class, forVariable(variable));
    }

    public QOrderTransactionEntity(Path<? extends OrderTransactionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderTransactionEntity(PathMetadata metadata) {
        super(OrderTransactionEntity.class, metadata);
    }

}

