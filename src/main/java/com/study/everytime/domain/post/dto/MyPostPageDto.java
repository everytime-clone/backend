package com.study.everytime.domain.post.dto;

import java.time.LocalDateTime;

public record MyPostPageDto(
        Long id,
        String boardName,
        String title,
        String content,
        LocalDateTime createdAt,
        String username,
        Long likeCount
) {

}