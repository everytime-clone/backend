package com.study.everytime.global.auth.service;

import com.study.everytime.domain.user.entity.User;
import com.study.everytime.domain.user.repository.UserRepository;
import com.study.everytime.global.auth.dto.SignInDto;
import com.study.everytime.global.auth.dto.SignUpDto;
import com.study.everytime.global.auth.exception.AuthException;
import com.study.everytime.global.auth.util.JwtProperties;
import com.study.everytime.global.auth.util.JwtUtils;
import com.study.everytime.global.redis.domain.Token;
import com.study.everytime.global.redis.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtils jwtUtils;
    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public SignInDto.Response issueToken(SignInDto.Request request) {
        User user = userRepository.findByProviderAndSub(request.provider(), request.sub())
                .orElseThrow(AuthException.NotJoinedUserException::new);

        JwtUtils.TokenPair tokenPair = createTokenPair(user.getId());
        return new SignInDto.Response(tokenPair.accessToken(), tokenPair.RefreshToken());
    }

    public void join(SignUpDto.Request request) {
        User user = User.of(request.username(), request.email(), request.provider(), request.sub());
        userRepository.save(user);
    }

    private JwtUtils.TokenPair createTokenPair(Long userId) {
        JwtUtils.TokenPair tokenPair = jwtUtils.createTokenPair(userId);
        tokenRepository.save(Token.of(userId, tokenPair.RefreshToken(), jwtProperties.refreshExpiration()));
        return tokenPair;
    }

}
