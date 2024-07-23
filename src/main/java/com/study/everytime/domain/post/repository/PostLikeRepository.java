package com.study.everytime.domain.post.repository;

import com.study.everytime.domain.post.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByUser_IdAndPost_Id(Long userId, Long postId);
    List<PostLike> findByPost_Id(Long postId);
}
