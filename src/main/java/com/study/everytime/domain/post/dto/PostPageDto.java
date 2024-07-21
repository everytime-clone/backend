package com.study.everytime.domain.post.dto;

import com.study.everytime.domain.post.entity.Post;

public interface PostPageDto {
    Post getPost();
    Long getLikeCount();
}
