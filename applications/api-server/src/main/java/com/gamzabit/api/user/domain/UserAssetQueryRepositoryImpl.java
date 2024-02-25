package com.gamzabit.api.user.domain;

import static com.gamzabit.api.user.domain.QUserAssetEntity.*;

import java.util.List;

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
            .on(QUserEntity.userEntity.id.eq(userId))
            .fetch();
    }
}
