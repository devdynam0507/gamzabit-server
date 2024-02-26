package com.gamzabit.domain.asset;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSymbolEntity is a Querydsl query type for SymbolEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSymbolEntity extends EntityPathBase<SymbolEntity> {

    private static final long serialVersionUID = -805537195L;

    public static final QSymbolEntity symbolEntity = new QSymbolEntity("symbolEntity");

    public final com.gamzabit.domain.common.QEntityBase _super = new com.gamzabit.domain.common.QEntityBase(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<java.math.BigDecimal> currentValue = createNumber("currentValue", java.math.BigDecimal.class);

    public final BooleanPath delisted = createBoolean("delisted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath symbolDisplayName = createString("symbolDisplayName");

    public final StringPath symbolName = createString("symbolName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QSymbolEntity(String variable) {
        super(SymbolEntity.class, forVariable(variable));
    }

    public QSymbolEntity(Path<? extends SymbolEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSymbolEntity(PathMetadata metadata) {
        super(SymbolEntity.class, metadata);
    }

}

