package com.study.everytime.global.auth.dto;

public class ReissueDto {

    public record Request(
            String refreshToken
    ) {
    }

    public record Response(
            String accessToken,
            String refreshToken
    ) {
    }

}
