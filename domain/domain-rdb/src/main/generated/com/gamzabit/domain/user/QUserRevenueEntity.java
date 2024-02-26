package com.gamzabit.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserRevenueEntity is a Querydsl query type for UserRevenueEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserRevenueEntity extends EntityPathBase<UserRevenueEntity> {

    private static final long serialVersionUID = 3310573L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserRevenueEntity userRevenueEntity = new QUserRevenueEntity("userRevenueEntity");

    public final com.gamzabit.domain.common.QEntityBase _super = new com.gamzabit.domain.common.QEntityBase(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> totalEvaluationPrice = createNumber("totalEvaluationPrice", Long.class);

    public final NumberPath<Long> totalProfit = createNumber("totalProfit", Long.class);

    public final NumberPath<Long> totalPurchasePrice = createNumber("totalPurchasePrice", Long.class);

    public final NumberPath<Long> totalRevenueRate = createNumber("totalRevenueRate", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUserEntity user;

    public QUserRevenueEntity(String variable) {
        this(UserRevenueEntity.class, forVariable(variable), INITS);
    }

    public QUserRevenueEntity(Path<? extends UserRevenueEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserRevenueEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserRevenueEntity(PathMetadata metadata, PathInits inits) {
        this(UserRevenueEntity.class, metadata, inits);
    }

    public QUserRevenueEntity(Class<? extends UserRevenueEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user")) : null;
    }

}

