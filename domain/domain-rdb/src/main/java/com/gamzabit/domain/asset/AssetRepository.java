package com.gamzabit.domain.asset;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<AssetEntity, Long> {

    Boolean existsByIdAndDelistedFalse(Long id);

    List<AssetEntity> findAllByDelistedFalse();

    Optional<AssetEntity> findBySymbolName(String symbolName);
}
