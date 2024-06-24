package com.study.everytime.global.redis.domain;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash(value = "token")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Token {

    @Id
    private Long id;
    private String token;
    @TimeToLive
    private Long expiration;

    public static Token of(Long id, String token, Long expiration) {
        return new Token(id, token, expiration);
    }

}