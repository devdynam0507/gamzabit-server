package com.gamzabit.domain.user;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.asset.AssetEntity;
import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.asset.AssetRepository;
import com.gamzabit.domain.asset.AssetValidator;
import com.gamzabit.domain.asset.DefaultAssetTypes;
import com.gamzabit.domain.asset.exception.AssetNotFoundException;
import com.gamzabit.domain.user.vo.AggregatedUserAsset;
import com.gamzabit.domain.user.vo.User;
import com.gamzabit.domain.user.vo.UserAssetWithKrw;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAssetCalculator {

    private final UserAssetQueryRepository userAssetQueryRepository;
    private final AssetRepository assetRepository;
    private final AssetValidator assetValidator;

    public UserAssetWithKrw getAssetsKrwPrice(User user) {
        UserAssetEntity userEntity =
            userAssetQueryRepository.findByUserIdAndAssetName(user.id(), DefaultAssetTypes.KRW.name())
                .orElseThrow(() -> new AssetNotFoundException("KRW 자산을 찾을 수 없습니다.", DefaultAssetTypes.KRW.name()));
        AssetPrice assetPrice = userEntity.calculateTotalKrwPrice();

        return new UserAssetWithKrw(userEntity.toUserAsset(), assetPrice);
    }

    public UserAssetWithKrw convertToKrwFromAsset(User user, String assetName) {
        UserAssetEntity userAssetEntity =
            userAssetQueryRepository.findByUserIdAndAssetName(user.id(), assetName)
                .orElseThrow(() -> new AssetNotFoundException(assetName + "를 찾을 수 없습니다.", assetName));
        AssetEntity assetEntity = userAssetEntity.getAsset();
        assetValidator.validate(assetEntity);
        AssetPrice assetPrice = assetEntity.calculateBuyPrice(userAssetEntity.getAmount());

        return new UserAssetWithKrw(userAssetEntity.toUserAsset(), assetPrice);
    }

    public AggregatedUserAsset getTotalKrwPriceOwnAssets(User user) {
        List<UserAssetEntity> userAssets =
            userAssetQueryRepository.findAllByUserId(user.id());
        BigDecimal assetPriceBigDecimal = userAssets.stream()
            .map(userAssetEntity ->
                userAssetEntity.getAsset().calculateBuyPrice(
                    userAssetEntity.getAmount()).getPrice()
                )
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new AggregatedUserAsset(
            new AssetPrice(assetPriceBigDecimal),
            userAssets.stream().map(UserAssetEntity::toUserAsset).toList()
        );
    }

    public AssetPrice calculateBuyPrice(AssetAmount amount, Long assetId) {
        AssetEntity asset = assetRepository.findById(assetId)
            .orElseThrow(() -> new AssetNotFoundException(assetId + "를 찾을 수 없습니다.", String.valueOf(assetId)));
        assetValidator.validate(asset);

        return asset.calculateBuyPrice(amount);
    }

    public AssetPrice calculateBuyPrice(AssetAmount amount, Long assetId, BigDecimal assetPrice) {
        AssetEntity asset = assetRepository.findById(assetId)
            .orElseThrow(() -> new AssetNotFoundException(assetId + "를 찾을 수 없습니다.", String.valueOf(assetId)));
        assetValidator.validate(asset);

        return asset.calculateBuyPrice(amount, new AssetPrice(assetPrice));
    }
}
