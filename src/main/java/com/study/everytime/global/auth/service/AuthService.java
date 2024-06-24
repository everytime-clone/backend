package com.study.everytime.global.auth.service;

import com.study.everytime.domain.user.entity.User;
import com.study.everytime.domain.user.repository.UserRepository;
import com.study.everytime.global.auth.dto.SignInDto;
import com.study.everytime.global.auth.dto.SignUpDto;
import com.study.everytime.global.auth.exception.AuthException;
import com.study.everytime.global.auth.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public SignInDto.Response issueToken(SignInDto.Request request) {
        User user = userRepository.findByProviderAndSub(request.provider(), request.sub())
                .orElseThrow(AuthException.NotJoinedUserException::new);

        String accessToken = jwtUtils.createAccessToken(user.getId());
        String refreshToken = jwtUtils.createRefreshToken(user.getId());
        return new SignInDto.Response(accessToken, refreshToken);
    }

    public void join(SignUpDto.Request request) {
        User user = User.of(request.username(), request.email(), request.provider(), request.sub());
        userRepository.save(user);
    }

}
