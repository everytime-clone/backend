package com.study.everytime.domain.user.dto;

import com.study.everytime.domain.user.entity.Provider;

public record readUserDto(
        String username,
        String email,
        Provider provider
) {
}
