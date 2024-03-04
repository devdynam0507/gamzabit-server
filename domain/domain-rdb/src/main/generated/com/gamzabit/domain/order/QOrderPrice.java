package com.gamzabit.domain.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderPrice is a Querydsl query type for OrderPrice
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QOrderPrice extends BeanPath<OrderPrice> {

    private static final long serialVersionUID = -1175624521L;

    public static final QOrderPrice orderPrice = new QOrderPrice("orderPrice");

    public final NumberPath<java.math.BigDecimal> assetPriceKrw = createNumber("assetPriceKrw", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> orderPriceKrw = createNumber("orderPriceKrw", java.math.BigDecimal.class);

    public QOrderPrice(String variable) {
        super(OrderPrice.class, forVariable(variable));
    }

    public QOrderPrice(Path<? extends OrderPrice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderPrice(PathMetadata metadata) {
        super(OrderPrice.class, metadata);
    }

}

