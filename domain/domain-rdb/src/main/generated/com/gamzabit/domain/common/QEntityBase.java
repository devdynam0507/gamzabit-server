package com.gamzabit.domain.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEntityBase is a Querydsl query type for EntityBase
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QEntityBase extends EntityPathBase<EntityBase> {

    private static final long serialVersionUID = -1883648645L;

    public static final QEntityBase entityBase = new QEntityBase("entityBase");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QEntityBase(String variable) {
        super(EntityBase.class, forVariable(variable));
    }

    public QEntityBase(Path<? extends EntityBase> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEntityBase(PathMetadata metadata) {
        super(EntityBase.class, metadata);
    }

}

