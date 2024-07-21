package com.study.everytime.domain.post.repository;

import com.study.everytime.domain.post.dto.PostPageDto;
import com.study.everytime.domain.post.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("""
            select p as post,
                  (select count(l)
                   from Like l where l.post = p) as likeCount
            from Post p
            join fetch p.writer
            where p.board.id = :boardId
            """)
    Slice<PostPageDto> findByBoard_Id(Long boardId, Pageable pageable);

    @Query("""
            select p as post,
                  (select count(l)
                   from Like l where l.post = p) as likeCount
            from Post p
            join fetch p.board
            join fetch p.writer
            where p.writer.id = :userId
            """)
    Slice<PostPageDto> findByWriter_id(Long userId, Pageable pageable);

    @Query("""
            select p as post,
                  (select count(l)
                   from Like l where l.post = p) as likeCount
            from Post p
            join fetch p.board
            join fetch p.writer
            where p in (select s.post
                        from Scrap s
                        where s.user.id = :userId)
            """)
    Slice<PostPageDto> findByScrab_User_Id(Long userId, Pageable pageable);
}
