package com.gamzabit.domain.user;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.user.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validateUserExists(Long userId) {
        String userIdString = String.valueOf(userId);
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userIdString));
        if (user.getDeleted()) {
            throw new UserNotFoundException(userIdString);
        }
    }
}
