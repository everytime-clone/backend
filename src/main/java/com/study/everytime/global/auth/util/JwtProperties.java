package com.study.everytime.global.auth.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperties(
        String issuer,
        String secret,
        Long accessExpiration,
        Long refreshExpiration
) {
}
