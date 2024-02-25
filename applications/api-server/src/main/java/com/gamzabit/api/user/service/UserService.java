package com.gamzabit.api.user.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamzabit.api.user.domain.UserAssetEntity;
import com.gamzabit.api.user.domain.UserAssetRepository;
import com.gamzabit.api.user.domain.UserEntity;
import com.gamzabit.api.user.domain.UserRepository;
import com.gamzabit.api.user.exception.UserAlreadyExistsException;
import com.gamzabit.api.user.exception.UserNotFoundException;
import com.gamzabit.api.user.service.vo.User;
import com.gamzabit.api.user.service.dto.UserCreation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserAssetRepository userAssetRepository;

    public User createUser(UserCreation userCreation) {
        String email = userCreation.email();
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException(email);
        }
        UserEntity newUser = UserEntity.toUser(userCreation);
        UserEntity createdUser = userRepository.save(newUser);
        UserAssetEntity userAsset = UserAssetEntity.builder()
            .user(createdUser)
            .assetName("krw")
            .assetDisplayName("원")
            .amount(BigDecimal.valueOf(100000))
            .build();
        userAssetRepository.save(userAsset);

        return newUser.toUser();
    }

    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        return userOptional.map(UserEntity::toUser)
            .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Transactional(readOnly = true)
    public User findUserByNickname(String nickname) {
        Optional<UserEntity> userOptional = userRepository.findByNickname(nickname);

        return userOptional.map(UserEntity::toUser)
            .orElseThrow(() -> new UserNotFoundException(nickname));
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        return userOptional.map(UserEntity::toUser)
            .orElseThrow(() -> new UserNotFoundException(String.valueOf(id)));
    }
}
