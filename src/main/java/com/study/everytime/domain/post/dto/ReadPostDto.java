package com.study.everytime.domain.post.dto;

import com.study.everytime.domain.post.entity.PostLike;
import com.study.everytime.domain.post.entity.Post;
import com.study.everytime.domain.post.entity.Scrap;

import java.time.LocalDateTime;
import java.util.List;

public record ReadPostDto(
        Long id,
        String boardName,
        String title,
        String content,
        LocalDateTime createdAt,
        String username,
        int likeCount,
        int scrapCount,
        boolean isQuestion,
        boolean isWriter,
        boolean isLiked,
        boolean isScraped
) {

    public static ReadPostDto of(Long userId, Post post, List<PostLike> postLikes, List<Scrap> scraps) {
        return new ReadPostDto(
                post.getId(),
                post.getBoard().getName(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getAnonymous() ? "익명" : post.getWriter().getUsername(),
                postLikes.size(),
                scraps.size(),
                post.getQuestion(),
                post.getWriter().getId().equals(userId),
                postLikes.stream().anyMatch(l -> l.getUser().getId().equals(userId)),
                scraps.stream().anyMatch(s -> s.getUser().getId().equals(userId))
        );
    }

}