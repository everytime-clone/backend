package com.study.everytime.domain.post.repository;

import com.study.everytime.domain.post.dto.BoardPostPageDto;
import com.study.everytime.domain.post.dto.MyPostPageDto;
import com.study.everytime.domain.post.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("""
            select new com.study.everytime.domain.post.dto.BoardPostPageDto(
                p.id, p.title, p.content, p.createdAt, p.writer.username, count(l))
            from Post p
            left join Like l on l.post = p
            where p.board.id = :boardId
            group by p.id
            """)
    Slice<BoardPostPageDto> findByBoard_Id(Long boardId, Pageable pageable);

    @Query("""
            select new com.study.everytime.domain.post.dto.MyPostPageDto(
                p.id, b.name, p.title, p.content, p.createdAt, p.writer.username, count(l))
            from Post p
            join Board b on b = p.board
            left join Like l on l.post = p
            where p.writer.id = :userId
            group by p.id
            """)
    Slice<MyPostPageDto> findByWriter_id(Long userId, Pageable pageable);
}
