package com.gamzabit.domain.asset;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAssetEntity is a Querydsl query type for AssetEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAssetEntity extends EntityPathBase<AssetEntity> {

    private static final long serialVersionUID = -209305351L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAssetEntity assetEntity = new QAssetEntity("assetEntity");

    public final com.gamzabit.domain.common.QEntityBase _super = new com.gamzabit.domain.common.QEntityBase(this);

    public final QAssetPrice assetPrice;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<AssetEntity.ListedType> listedType = createEnum("listedType", AssetEntity.ListedType.class);

    public final QSymbol symbol;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QAssetEntity(String variable) {
        this(AssetEntity.class, forVariable(variable), INITS);
    }

    public QAssetEntity(Path<? extends AssetEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAssetEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAssetEntity(PathMetadata metadata, PathInits inits) {
        this(AssetEntity.class, metadata, inits);
    }

    public QAssetEntity(Class<? extends AssetEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.assetPrice = inits.isInitialized("assetPrice") ? new QAssetPrice(forProperty("assetPrice")) : null;
        this.symbol = inits.isInitialized("symbol") ? new QSymbol(forProperty("symbol")) : null;
    }

}

