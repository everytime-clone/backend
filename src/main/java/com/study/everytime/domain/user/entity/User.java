package com.study.everytime.domain.user.entity;

import com.study.everytime.domain.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(indexes = @Index(name = "idx__provider__sub", columnList = "provider, sub"))
@SQLDelete(sql = "UPDATE user SET deleted_at = NOW() WHERE user_id=?")
@SQLRestriction("deleted_at is NULL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String email;

    private Provider provider;
    private String sub;

    private LocalDateTime deletedAt;

    public User(String username, String email, Provider provider, String sub) {
        this.username = username;
        this.email = email;
        this.provider = provider;
        this.sub = sub;
    }

    public void update(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
