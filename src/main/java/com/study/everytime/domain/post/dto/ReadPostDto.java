package com.study.everytime.domain.post.dto;

import java.time.LocalDateTime;

public record ReadPostDto(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        String username,
        Long likes,
        Long scraps,
        Boolean question
) {

}