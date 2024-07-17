package com.study.everytime.domain.post.entity;

import com.study.everytime.domain.auditing.BaseEntity;
import com.study.everytime.domain.board.entity.Board;
import com.study.everytime.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@SQLDelete(sql = "UPDATE post SET deleted_at = NOW() WHERE post_id=?")
@SQLRestriction("deleted_at is NULL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private User writer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private LocalDateTime deletedAt;

    public Post(String title, String content, User writer, Board board) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.board = board;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
