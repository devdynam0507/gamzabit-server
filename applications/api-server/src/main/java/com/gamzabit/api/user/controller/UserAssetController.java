package com.gamzabit.api.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamzabit.api.infrastructure.security.dto.AuthenticatedUser;
import com.gamzabit.api.user.service.assets.UserAssetReader;
import com.gamzabit.api.user.service.assets.vo.UserAsset;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user/assets")
@RequiredArgsConstructor
public class UserAssetController {

    private final UserAssetReader userAssetReader;

    @GetMapping
    public List<UserAsset> getAllUserAssets(AuthenticatedUser user) {
        return userAssetReader.getUserAssets(user.getUser());
    }

    @GetMapping("/{symbolName}")
    public UserAsset getSpecificSymbolUserAsset(AuthenticatedUser user, @PathVariable("symbolName") String symbolName) {
        return userAssetReader.getSpecificSymbolUserAsset(user.getUser(), symbolName.toUpperCase());
    }
}
