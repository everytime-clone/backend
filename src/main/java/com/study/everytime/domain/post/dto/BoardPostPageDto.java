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
    public static BoardPostPageDto from(PostPageDto dto) {
        return new BoardPostPageDto(
                dto.getPost().getId(),
                dto.getPost().getTitle(),
                dto.getPost().getContent(),
                dto.getPost().getCreatedAt(),
                dto.getPost().getAnonymous() ? "익명" : dto.getPost().getWriter().getUsername(),
                dto.getLikeCount()
        );
    }
}