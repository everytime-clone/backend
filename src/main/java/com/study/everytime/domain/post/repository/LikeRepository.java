package com.study.everytime.domain.post.repository;

import com.study.everytime.domain.post.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUser_IdAndPost_Id(Long userId, Long postId);
    List<Like> findByPost_Id(Long postId);
}
