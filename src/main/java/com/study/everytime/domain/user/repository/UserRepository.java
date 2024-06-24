package com.study.everytime.domain.user.repository;

import com.study.everytime.domain.user.entity.User;
import com.study.everytime.domain.user.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderAndSub(Provider provider, String sub);
}
