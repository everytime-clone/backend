package com.study.everytime.domain.post.repository;

import com.study.everytime.domain.post.dto.PostInformDto;
import com.study.everytime.domain.post.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("""
            select new com.study.everytime.domain.post.dto.PostInformDto(
                p.id, p.title, p.content, p.createdAt, p.writer.username, count(l), count(s), p.question, p.anonymous)
            from Post p
            left join Like l on l.post = p
            left join Scrap s on s.post = p
            where p.id = :postId
            group by p.id
            """)
    Optional<PostInformDto> findReadPostDtoById(Long postId);

    @Query("""
            select new com.study.everytime.domain.post.dto.PostInformDto(
                p.id, p.title, p.content, p.createdAt, p.writer.username, count(l), count(s), p.question, p.anonymous)
            from Post p
            left join Like l on l.post = p
            left join Scrap s on s.post = p
            where p.board.id = :boardId
            group by p.id
            """)
    Slice<PostInformDto> findByBoard_Id(Long boardId, Pageable pageable);
}
