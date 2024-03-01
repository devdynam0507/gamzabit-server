package com.gamzabit.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAssetAmount is a Querydsl query type for AssetAmount
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAssetAmount extends BeanPath<AssetAmount> {

    private static final long serialVersionUID = 2071165185L;

    public static final QAssetAmount assetAmount = new QAssetAmount("assetAmount");

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public QAssetAmount(String variable) {
        super(AssetAmount.class, forVariable(variable));
    }

    public QAssetAmount(Path<? extends AssetAmount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAssetAmount(PathMetadata metadata) {
        super(AssetAmount.class, metadata);
    }

}

