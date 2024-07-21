package com.study.everytime.domain.post.repository;

import com.study.everytime.domain.post.entity.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    boolean existsByUser_IdAndPost_Id(Long userId, Long postId);
    Optional<Scrap> findByUser_IdAndPost_Id(Long userId, Long postId);
    List<Scrap> findByPost_Id(Long postId);
}
