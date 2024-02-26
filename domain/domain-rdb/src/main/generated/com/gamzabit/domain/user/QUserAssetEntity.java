package com.gamzabit.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAssetEntity is a Querydsl query type for UserAssetEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAssetEntity extends EntityPathBase<UserAssetEntity> {

    private static final long serialVersionUID = -1917282495L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAssetEntity userAssetEntity = new QUserAssetEntity("userAssetEntity");

    public final com.gamzabit.domain.common.QEntityBase _super = new com.gamzabit.domain.common.QEntityBase(this);

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.gamzabit.domain.asset.QSymbolEntity symbol;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUserEntity user;

    public QUserAssetEntity(String variable) {
        this(UserAssetEntity.class, forVariable(variable), INITS);
    }

    public QUserAssetEntity(Path<? extends UserAssetEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAssetEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAssetEntity(PathMetadata metadata, PathInits inits) {
        this(UserAssetEntity.class, metadata, inits);
    }

    public QUserAssetEntity(Class<? extends UserAssetEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.symbol = inits.isInitialized("symbol") ? new com.gamzabit.domain.asset.QSymbolEntity(forProperty("symbol")) : null;
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user")) : null;
    }

}

