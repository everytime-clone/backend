package com.study.everytime.domain.bookmark.repository;

import com.study.everytime.domain.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUser_Id(Long userId);
    Optional<Bookmark> findByUser_IdAndBoard_Id(Long userId, Long boardId);
}
