package com.gamzabit.domain.user;

import static com.gamzabit.domain.user.QUserAssetEntity.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserAssetQueryRepositoryImpl implements UserAssetQueryRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<UserAssetEntity> findAllByUserId(Long userId) {
        return queryFactory
            .select(userAssetEntity)
            .from(userAssetEntity)
            .leftJoin(QUserEntity.userEntity)
            .on(QUserEntity.userEntity.id.id.eq(userId))
            .fetch();
    }

    @Override
    public Optional<UserAssetEntity> findByUserIdAndAssetName(Long userId, String symbolName) {
        return Optional.ofNullable(
            queryFactory
                .select(userAssetEntity)
                .from(userAssetEntity)
                .where(
                    userAssetEntity.user.id.id.eq(userId)
                       .and(
                           userAssetEntity.asset.symbol.symbolName.eq(symbolName)
                       )
                )
                .fetchOne()
        );
    }
}
