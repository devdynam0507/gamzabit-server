package com.gamzabit.domain.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gamzabit.domain.user.exception.UserAlreadyExistsException;
import com.gamzabit.domain.user.vo.User;
import com.gamzabit.domain.user.vo.UserCreation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCreator {

    private final UserRepository userRepository;

    public User createUser(UserCreation userCreation) {
        String email = userCreation.email();
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException(email);
        }
        UserEntity newUser = UserEntity.of(userCreation);
        UserEntity createdUser = userRepository.save(newUser);

        return createdUser.toUser();
    }
}
