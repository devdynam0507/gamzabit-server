package com.gamzabit.domain.user;

import com.gamzabit.domain.user.exception.UserNotFoundException;

public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUserExists(UserId userId) {
        String userIdString = String.valueOf(userId);
        UserEntity user = userRepository.findById(userId.longValue())
            .orElseThrow(() -> new UserNotFoundException(userIdString));
        if (user.getDeleted()) {
            throw new UserNotFoundException(userIdString);
        }
    }
}
