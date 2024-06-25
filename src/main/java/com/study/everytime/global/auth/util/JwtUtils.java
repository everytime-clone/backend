package com.study.everytime.global.auth.util;

import com.study.everytime.global.auth.exception.AuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private final String TOKEN_TYPE = "token_type";
    private final String ACCESS = "access";
    private final String REFRESH = "refresh";

    private final SecretKey secretKey;
    private final JwtProperties jwtProperties;

    public JwtUtils(JwtProperties jwtProperties) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.secret()));
        this.jwtProperties = jwtProperties;
    }

    private String createToken(String type, Long expireTime, Long userId) {
        return Jwts.builder()
                .issuer(jwtProperties.issuer())
                .subject(userId.toString())
                .claim(TOKEN_TYPE, type)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(secretKey)
                .compact();
    }

    public TokenPair createTokenPair(Long userId) {
        String accessToken = createAccessToken(userId);
        String refreshToken = createRefreshToken(userId);
        return new TokenPair(accessToken, refreshToken);
    }

    private String createAccessToken(Long userId) {
        return createToken(ACCESS, jwtProperties.accessExpiration(), userId);
    }

    private String createRefreshToken(Long userId) {
        return createToken(REFRESH, jwtProperties.refreshExpiration(), userId);
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new AuthException.InvalidTokenException();
        }
    }

    public Long parseAccessToken(String token) {
        Claims claims = getClaims(token);
        String type = claims.get(TOKEN_TYPE, String.class);

        if (!type.equals(ACCESS))
            throw new AuthException.InvalidTokenException();

        return Long.valueOf(claims.getSubject());
    }

    public Long parseRefreshToken(String token) {
        Claims claims = getClaims(token);
        String type = claims.get(TOKEN_TYPE, String.class);

        if (!type.equals(REFRESH))
            throw new AuthException.InvalidTokenException();

        return Long.valueOf(claims.getSubject());
    }

    public record TokenPair(
            String accessToken,
            String RefreshToken
    ) {
    }

}
