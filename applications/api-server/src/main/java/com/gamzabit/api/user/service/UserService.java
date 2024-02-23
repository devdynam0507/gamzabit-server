package com.gamzabit.api.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gamzabit.api.user.domain.UserEntity;
import com.gamzabit.api.user.domain.UserRepository;
import com.gamzabit.api.user.exception.UserAlreadyExistsException;
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
}
