package com.gamzabit.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFreezeAssetRepository extends JpaRepository<UserFreezeAssetEntity, Long> {

    void deleteByOrderId(Long orderId);
}
