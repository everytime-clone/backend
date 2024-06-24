package com.study.everytime.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "idx__provider__sub", columnList = "provider, sub"))
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String email;

    private Provider provider;
    private String sub;

    private User(String username, String email, Provider provider, String sub) {
        this.username = username;
        this.email = email;
        this.provider = provider;
        this.sub = sub;
    }

    public static User of(String username, String email, Provider provider, String sub) {
        return new User(username, email, provider, sub);
    }

}
