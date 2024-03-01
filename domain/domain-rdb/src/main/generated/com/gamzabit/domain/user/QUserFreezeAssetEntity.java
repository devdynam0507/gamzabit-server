package com.gamzabit.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserFreezeAssetEntity is a Querydsl query type for UserFreezeAssetEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserFreezeAssetEntity extends EntityPathBase<UserFreezeAssetEntity> {

    private static final long serialVersionUID = -72852054L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserFreezeAssetEntity userFreezeAssetEntity = new QUserFreezeAssetEntity("userFreezeAssetEntity");

    public final com.gamzabit.domain.common.QEntityBase _super = new com.gamzabit.domain.common.QEntityBase(this);

    public final NumberPath<Long> assetId = createNumber("assetId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QAssetAmount freezeAmount;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> txDate = createDateTime("txDate", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserFreezeAssetEntity(String variable) {
        this(UserFreezeAssetEntity.class, forVariable(variable), INITS);
    }

    public QUserFreezeAssetEntity(Path<? extends UserFreezeAssetEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserFreezeAssetEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserFreezeAssetEntity(PathMetadata metadata, PathInits inits) {
        this(UserFreezeAssetEntity.class, metadata, inits);
    }

    public QUserFreezeAssetEntity(Class<? extends UserFreezeAssetEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.freezeAmount = inits.isInitialized("freezeAmount") ? new QAssetAmount(forProperty("freezeAmount")) : null;
    }

}

