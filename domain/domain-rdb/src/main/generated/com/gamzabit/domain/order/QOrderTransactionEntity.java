package com.gamzabit.domain.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderTransactionEntity is a Querydsl query type for OrderTransactionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderTransactionEntity extends EntityPathBase<OrderTransactionEntity> {

    private static final long serialVersionUID = 1640727247L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderTransactionEntity orderTransactionEntity = new QOrderTransactionEntity("orderTransactionEntity");

    public final com.gamzabit.domain.common.QEntityBase _super = new com.gamzabit.domain.common.QEntityBase(this);

    public final com.gamzabit.domain.asset.QAssetPrice concludedKrw;

    public final com.gamzabit.domain.user.QAssetAmount concludedQuantity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QOrderTransactionEntity(String variable) {
        this(OrderTransactionEntity.class, forVariable(variable), INITS);
    }

    public QOrderTransactionEntity(Path<? extends OrderTransactionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderTransactionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderTransactionEntity(PathMetadata metadata, PathInits inits) {
        this(OrderTransactionEntity.class, metadata, inits);
    }

    public QOrderTransactionEntity(Class<? extends OrderTransactionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.concludedKrw = inits.isInitialized("concludedKrw") ? new com.gamzabit.domain.asset.QAssetPrice(forProperty("concludedKrw")) : null;
        this.concludedQuantity = inits.isInitialized("concludedQuantity") ? new com.gamzabit.domain.user.QAssetAmount(forProperty("concludedQuantity")) : null;
    }

}

