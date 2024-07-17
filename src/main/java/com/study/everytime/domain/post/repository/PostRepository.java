package com.study.everytime.domain.post.repository;

import com.study.everytime.domain.post.dto.ReadPostDto;
import com.study.everytime.domain.post.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("""
            select new com.study.everytime.domain.post.dto.ReadPostDto(p.id, p.title, p.content, p.createdAt, p.writer.username, count(l), count(s))
            from Post p
            left join Like l on l.post = p
            left join Scrap s on s.post = p
            where p.id = :postId
            group by p.id
            """)
    Optional<ReadPostDto> findReadPostDtoById(Long postId);

    @Query("""
            select new com.study.everytime.domain.post.dto.ReadPostDto(p.id, p.title, p.content, p.createdAt, p.writer.username, count(l), count(s))
            from Post p
            left join Like l on l.post = p
            left join Scrap s on s.post = p
            where p.board.id = :boardId
            group by p.id
            """)
    Slice<ReadPostDto> findByBoard_Id(Long boardId, Pageable pageable);
}
