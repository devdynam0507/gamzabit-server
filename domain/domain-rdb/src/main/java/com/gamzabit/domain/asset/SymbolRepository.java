package com.gamzabit.domain.asset;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SymbolRepository extends JpaRepository<SymbolEntity, Long> {

    Boolean existsByIdAndDelistedFalse(Long id);

    List<SymbolEntity> findAllByDelistedFalse();
}
