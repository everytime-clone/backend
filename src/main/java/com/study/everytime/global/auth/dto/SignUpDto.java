package com.study.everytime.global.auth.dto;

import com.study.everytime.domain.user.entity.Provider;

public class SignUpDto {

    public record Request(
            String username,
            String email,
            Provider provider,
            String sub
    ) {
    }

}
