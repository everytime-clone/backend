package com.study.everytime.global.redis.repository;

import com.study.everytime.global.redis.domain.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Long> {
}
