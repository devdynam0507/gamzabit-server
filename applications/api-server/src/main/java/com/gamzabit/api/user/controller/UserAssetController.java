package com.gamzabit.api.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamzabit.api.infrastructure.common.Responses;
import com.gamzabit.api.infrastructure.security.dto.AuthenticatedUser;

import com.gamzabit.api.user.service.UserAssetCalculateService;
import com.gamzabit.domain.user.vo.AggregatedUserAsset;
import com.gamzabit.domain.user.vo.UserAssetWithKrw;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user/assets")
@RequiredArgsConstructor
public class UserAssetController {

    private final UserAssetCalculateService userAssetCalculator;

    @GetMapping
    public Responses<AggregatedUserAsset> getAllUserAssets(AuthenticatedUser user) {
        AggregatedUserAsset aggregatedUserAsset = userAssetCalculator.aggregateAssetsToKrw(user.getUser());

        return Responses.ok("유저 자산목록을 성공적으로 조회하였습니다.", aggregatedUserAsset);
    }

    @GetMapping("/{symbolName}")
    public Responses<UserAssetWithKrw> getSpecificSymbolUserAsset(
        AuthenticatedUser user,
        @PathVariable("symbolName") String symbolName
    ) {
        UserAssetWithKrw userAssetWithKrw = userAssetCalculator.aggregateAssetWithKrw(user.getUser(), symbolName);

        return Responses.ok("유저의 자산을 성공적으로 조회하였습니다.", userAssetWithKrw);
    }
}
