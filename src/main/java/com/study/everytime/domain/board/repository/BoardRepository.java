package com.study.everytime.domain.board.repository;

import com.study.everytime.domain.board.entity.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Slice<Board> findByNameContains(String name, Pageable pageable);
}
