package com.gamzabit.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAssetTransactionEntity is a Querydsl query type for UserAssetTransactionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAssetTransactionEntity extends EntityPathBase<UserAssetTransactionEntity> {

    private static final long serialVersionUID = -1704482237L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAssetTransactionEntity userAssetTransactionEntity = new QUserAssetTransactionEntity("userAssetTransactionEntity");

    public final com.gamzabit.domain.common.QEntityBase _super = new com.gamzabit.domain.common.QEntityBase(this);

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final EnumPath<com.gamzabit.domain.user.types.AssetTransactionType> assetTransactionType = createEnum("assetTransactionType", com.gamzabit.domain.user.types.AssetTransactionType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath destinationAddress = createString("destinationAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUserEntity user;

    public final QUserAssetEntity userAsset;

    public QUserAssetTransactionEntity(String variable) {
        this(UserAssetTransactionEntity.class, forVariable(variable), INITS);
    }

    public QUserAssetTransactionEntity(Path<? extends UserAssetTransactionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAssetTransactionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAssetTransactionEntity(PathMetadata metadata, PathInits inits) {
        this(UserAssetTransactionEntity.class, metadata, inits);
    }

    public QUserAssetTransactionEntity(Class<? extends UserAssetTransactionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
        this.userAsset = inits.isInitialized("userAsset") ? new QUserAssetEntity(forProperty("userAsset"), inits.get("userAsset")) : null;
    }

}

