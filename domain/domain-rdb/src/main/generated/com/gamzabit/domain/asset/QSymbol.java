package com.gamzabit.domain.asset;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSymbol is a Querydsl query type for Symbol
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QSymbol extends BeanPath<Symbol> {

    private static final long serialVersionUID = -1848356910L;

    public static final QSymbol symbol = new QSymbol("symbol");

    public final StringPath symbolDisplayName = createString("symbolDisplayName");

    public final StringPath symbolName = createString("symbolName");

    public QSymbol(String variable) {
        super(Symbol.class, forVariable(variable));
    }

    public QSymbol(Path<? extends Symbol> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSymbol(PathMetadata metadata) {
        super(Symbol.class, metadata);
    }

}

