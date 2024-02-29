package com.gamzabit.domain.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFreezeAssetProcessor {

    private final UserFreezeAssetRepository freezeAssetRepository;
}
