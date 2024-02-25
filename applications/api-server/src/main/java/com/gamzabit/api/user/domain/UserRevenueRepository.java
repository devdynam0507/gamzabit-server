package com.gamzabit.api.user.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRevenueRepository extends JpaRepository<UserRevenueEntity, Long> {

    List<UserRevenueEntity> findByUser_id(Long userId);
}
