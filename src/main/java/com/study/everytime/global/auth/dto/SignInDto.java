package com.study.everytime.global.auth.dto;

import com.study.everytime.domain.user.entity.Provider;

public class SignInDto {

    public record Request(
            Provider provider,
            String sub
    ) {
    }

    public record Response(
            String accessToken,
            String refreshToken
    ) {
    }

}
