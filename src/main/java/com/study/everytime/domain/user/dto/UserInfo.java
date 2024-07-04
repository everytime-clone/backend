package com.study.everytime.domain.user.dto;

import com.study.everytime.domain.user.entity.Provider;

public class UserInfo {

    public record Response(
            Long id,
            String username,
            String email,
            Provider provider,
            String sub
    ) {
    }

}
