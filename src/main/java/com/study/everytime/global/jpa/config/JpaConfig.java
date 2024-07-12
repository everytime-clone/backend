package com.study.everytime.global.jpa.config;

import com.study.everytime.global.jpa.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {

    @Bean
    public AuditorAwareImpl auditorAware(){
        return new AuditorAwareImpl();
    }

}
