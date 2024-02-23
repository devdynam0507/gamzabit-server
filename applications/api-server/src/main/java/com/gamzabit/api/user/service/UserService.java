package com.gamzabit.api.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gamzabit.api.user.domain.UserEntity;
import com.gamzabit.api.user.domain.UserRepository;
import com.gamzabit.api.user.exception.UserAlreadyExistsException;
import com.gamzabit.api.user.exception.UserNotFoundException;
import com.gamzabit.api.user.service.dto.User;
import com.gamzabit.api.user.service.vo.UserCreationVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(UserCreationVO userCreationVO) {
        String email = userCreationVO.email();
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException(email);
        }
        UserEntity newUser = UserEntity.toUser(userCreationVO);
        userRepository.save(newUser);
    }

    public User findUserByEmail(String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

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
