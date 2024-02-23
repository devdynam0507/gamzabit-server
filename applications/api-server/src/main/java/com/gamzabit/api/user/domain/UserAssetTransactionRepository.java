package com.gamzabit.api.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAssetTransactionRepository extends JpaRepository<UserAssetTransactionEntity, Long> {
}
