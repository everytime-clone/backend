package com.study.everytime.global.auth.service;

import com.study.everytime.domain.user.entity.User;
import com.study.everytime.domain.user.repository.UserRepository;
import com.study.everytime.global.auth.dto.ReissueDto;
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

        TokenPair tokenPair = createTokenPair(user.getId());
        return new SignInDto.Response(tokenPair.accessToken(), tokenPair.RefreshToken());
    }

    public void join(SignUpDto.Request request) {
        if (userRepository.existsByProviderAndSub(request.provider(), request.sub())) {
            throw new AuthException.AlreadyJoinedUserException();
        }

        User user = new User(request.username(), request.email(), request.provider(), request.sub());
        userRepository.save(user);
    }

    public ReissueDto.Response reissueToken(ReissueDto.Request request) {
        String refreshToken = request.refreshToken();
        Long userId = jwtUtils.parseRefreshToken(refreshToken);

        Token token = tokenRepository.findById(userId)
                .orElseThrow(AuthException.InvalidTokenException::new);

        if (!token.getToken().equals(refreshToken)) {
            throw new AuthException.InvalidTokenException();
        }

        TokenPair tokenPair = createTokenPair(userId);
        return new ReissueDto.Response(tokenPair.accessToken(), tokenPair.RefreshToken());
    }

    private TokenPair createTokenPair(Long userId) {
        String accessToken = jwtUtils.createAccessToken(userId);
        String refreshToken = jwtUtils.createRefreshToken(userId);

        TokenPair tokenPair = new TokenPair(accessToken, refreshToken);
        tokenRepository.save(Token.of(userId, tokenPair.RefreshToken(), jwtProperties.refreshExpiration()));
        return tokenPair;
    }

    public record TokenPair(
            String accessToken,
            String RefreshToken
    ) {
    }

}
