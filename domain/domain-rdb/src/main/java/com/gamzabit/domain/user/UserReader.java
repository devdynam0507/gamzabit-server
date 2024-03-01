package com.gamzabit.domain.user;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamzabit.domain.user.exception.UserNotFoundException;
import com.gamzabit.domain.user.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserReader {

    private final UserRepository userRepository;

    public User findUserByEmail(String email) {
        Optional<UserEntity> userOptional = userRepository.findByUserCredentials_Email(email);

        return userOptional.map(UserEntity::toUser)
                           .orElseThrow(() -> new UserNotFoundException(email));
    }

    public User findUserByNickname(String nickname) {
        Optional<UserEntity> userOptional = userRepository.findByNickname(nickname);

        return userOptional.map(UserEntity::toUser)
                           .orElseThrow(() -> new UserNotFoundException(nickname));
    }

    public User findUserById(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        return userOptional.map(UserEntity::toUser)
                           .orElseThrow(() -> new UserNotFoundException(String.valueOf(id)));
    }
}
