package com.study.everytime.domain.bookmark.entity;

import com.study.everytime.domain.board.entity.Board;
import com.study.everytime.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Bookmark(User user, Board board) {
        this.user = user;
        this.board = board;
    }

}
