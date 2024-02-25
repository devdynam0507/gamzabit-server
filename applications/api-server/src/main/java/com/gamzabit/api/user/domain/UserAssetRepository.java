package com.gamzabit.api.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAssetRepository extends JpaRepository<UserAssetEntity, Long> {
}
