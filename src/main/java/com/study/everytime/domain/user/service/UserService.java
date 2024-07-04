package com.study.everytime.domain.user.service;

import com.study.everytime.domain.user.dto.UserInfo;
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
    public UserInfo.Response getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserException.UserNotFoundException::new);

        return new UserInfo.Response(user.getId(), user.getUsername(), user.getEmail(), user.getProvider(), user.getSub());
    }

}
