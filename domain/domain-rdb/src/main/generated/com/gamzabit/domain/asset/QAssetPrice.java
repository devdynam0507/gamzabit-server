package com.gamzabit.domain.asset;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAssetPrice is a Querydsl query type for AssetPrice
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAssetPrice extends BeanPath<AssetPrice> {

    private static final long serialVersionUID = 1527535987L;

    public static final QAssetPrice assetPrice = new QAssetPrice("assetPrice");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public QAssetPrice(String variable) {
        super(AssetPrice.class, forVariable(variable));
    }

    public QAssetPrice(Path<? extends AssetPrice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAssetPrice(PathMetadata metadata) {
        super(AssetPrice.class, metadata);
    }

}

