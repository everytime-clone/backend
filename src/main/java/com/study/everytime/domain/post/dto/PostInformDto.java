package com.study.everytime.domain.post.dto;

import java.time.LocalDateTime;

public record PostInformDto(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        Long writerId,
        String username,
        Long likes,
        Long scraps,
        Boolean question,
        Boolean anonymous
) {
}
