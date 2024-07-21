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
    public static MyPostPageDto from(PostPageDto dto) {
        return new MyPostPageDto(
                dto.getPost().getId(),
                dto.getPost().getBoard().getName(),
                dto.getPost().getTitle(),
                dto.getPost().getContent(),
                dto.getPost().getCreatedAt(),
                dto.getPost().getAnonymous() ? "익명" : dto.getPost().getWriter().getUsername(),
                dto.getLikeCount()
        );
    }
}