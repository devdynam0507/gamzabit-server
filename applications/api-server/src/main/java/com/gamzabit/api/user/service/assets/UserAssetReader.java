package com.gamzabit.api.user.service.assets;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gamzabit.api.asset.exception.AssetNotFoundException;
import com.gamzabit.api.user.domain.UserAssetEntity;
import com.gamzabit.api.user.domain.UserAssetQueryRepository;
import com.gamzabit.api.user.service.assets.vo.UserAsset;
import com.gamzabit.api.user.service.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAssetReader {

    private final UserAssetQueryRepository userAssetQueryRepository;

    public List<UserAsset> getUserAssets(User user) {
        List<UserAssetEntity> userAssets = userAssetQueryRepository.findAllByUserId(user.id());

        return userAssets.stream()
            .filter(userAssetEntity -> !userAssetEntity.getSymbol().getDelisted())
            .map(UserAsset::from)
            .toList();
    }

    public UserAsset getSpecificSymbolUserAsset(User user, String symbolName) {
        Optional<UserAssetEntity> userAssetEntityOptional =
            userAssetQueryRepository.findByUserIdAndSymbolName(user.id(), symbolName);
        if (userAssetEntityOptional.isEmpty()) {
            throw new AssetNotFoundException("유저의 '" + symbolName + "' 자산이 존재하지 않습니다.", symbolName);
        }

        return UserAsset.from(userAssetEntityOptional.get());
    }
}
