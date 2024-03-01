package com.gamzabit.domain.asset;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<AssetEntity, Long> {

    Optional<AssetEntity> findBySymbol_SymbolName(String symbolName);
}
