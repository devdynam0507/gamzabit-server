package com.gamzabit.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserCredentials_Email(String email);

    Optional<UserEntity> findByNickname(String nickname);
}
