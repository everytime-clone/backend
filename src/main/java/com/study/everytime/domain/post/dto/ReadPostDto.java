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

    public static ReadPostDto from(PostInformDto dto) {
        String displayName = dto.username();
        if (dto.anonymous()) {
            displayName = "익명";
        }

        return new ReadPostDto(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.createdAt(),
                displayName,
                dto.likes(),
                dto.scraps(),
                dto.question()
        );
    }

}