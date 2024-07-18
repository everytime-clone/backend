package com.study.everytime.domain.post.dto;

public record CreatePostDto(
        String title,
        String content,
        Boolean question,
        Boolean anonymous
) {
}
