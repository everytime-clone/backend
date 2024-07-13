package com.study.everytime.domain.board.entity;

import com.study.everytime.domain.auditing.BaseEntity;
import com.study.everytime.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@SQLDelete(sql = "UPDATE board SET deleted_at = NOW() WHERE board_id=?")
@SQLRestriction("deleted_at is NULL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    private LocalDateTime deletedAt;

    public Board(String name, String description, User admin) {
        this.name = name;
        this.description = description;
        this.admin = admin;
    }

    public void updateInform(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void updateAdmin(User admin) {
        this.admin = admin;
    }

}
