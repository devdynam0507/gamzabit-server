package com.gamzabit.api.user.service.assets;

import java.util.List;

import org.springframework.stereotype.Service;

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
            .map(UserAsset::from)
            .toList();
    }
}
