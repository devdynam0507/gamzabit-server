package com.gamzabit.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.asset.exception.AssetNotFoundException;
import com.gamzabit.domain.user.vo.User;
import com.gamzabit.domain.user.vo.UserAsset;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAssetReader {

    private final UserAssetQueryRepository userAssetQueryRepository;

    public List<UserAsset> getUserAssets(User user) {
        List<UserAssetEntity> userAssets = userAssetQueryRepository.findAllByUserId(user.id().longValue());

        return userAssets.stream()
            .filter(userAssetEntity -> userAssetEntity.getAsset().isDelisted())
            .map(UserAssetEntity::toUserAsset)
            .toList();
    }

    public UserAsset getSpecificSymbolUserAsset(User user, String symbolName) {
        Optional<UserAssetEntity> userAssetEntityOptional =
            userAssetQueryRepository.findByUserIdAndAssetName(user.id().longValue(), symbolName);
        if (userAssetEntityOptional.isEmpty()) {
            throw new AssetNotFoundException("유저의 '" + symbolName + "' 자산이 존재하지 않습니다.", symbolName);
        }

        return userAssetEntityOptional.get().toUserAsset();
    }
}
