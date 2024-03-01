package com.gamzabit.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserCredentials is a Querydsl query type for UserCredentials
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserCredentials extends BeanPath<UserCredentials> {

    private static final long serialVersionUID = 1818358602L;

    public static final QUserCredentials userCredentials = new QUserCredentials("userCredentials");

    public final StringPath email = createString("email");

    public final StringPath password = createString("password");

    public QUserCredentials(String variable) {
        super(UserCredentials.class, forVariable(variable));
    }

    public QUserCredentials(Path<? extends UserCredentials> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserCredentials(PathMetadata metadata) {
        super(UserCredentials.class, metadata);
    }

}

