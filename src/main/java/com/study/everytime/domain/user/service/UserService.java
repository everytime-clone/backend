package com.study.everytime.domain.user.service;

import com.study.everytime.domain.user.dto.UpdateUserDto;
import com.study.everytime.domain.user.dto.readUserDto;
import com.study.everytime.domain.user.entity.User;
import com.study.everytime.domain.user.exception.UserException;
import com.study.everytime.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public readUserDto readUser(Long userId) {
        User user = getUser(userId);
        return new readUserDto(user.getUsername(), user.getEmail(), user.getProvider());
    }

    public void updateUser(Long userId, UpdateUserDto dto) {
        User user = getUser(userId);
        user.update(dto.username(), dto.email());
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserException.UserNotFoundException::new);
    }

}
