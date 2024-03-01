package com.gamzabit.domain.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRevenueRepository extends JpaRepository<UserRevenueEntity, Long> {

    List<UserRevenueEntity> findByUser_Id(Long userId);
}
