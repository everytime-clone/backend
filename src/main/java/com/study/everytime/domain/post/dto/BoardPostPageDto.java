package com.study.everytime.domain.post.dto;

import java.time.LocalDateTime;

public record BoardPostPageDto(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        String username,
        Long likeCount
) {

}