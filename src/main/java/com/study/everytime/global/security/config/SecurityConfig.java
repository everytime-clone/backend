package com.study.everytime.global.security.config;

import com.study.everytime.global.security.filter.JwtExceptionHandlerFilter;
import com.study.everytime.global.security.filter.JwtFilter;
import com.study.everytime.global.auth.util.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                    JwtUtils jwtUtils) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterAfter(jwtFilter(jwtUtils), OAuth2LoginAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionHandlerFilter(), JwtFilter.class)
                .build();
    }

    JwtFilter jwtFilter(JwtUtils jwtUtils) {
        return new JwtFilter(jwtUtils);
    }

    JwtExceptionHandlerFilter jwtExceptionHandlerFilter() {
        return new JwtExceptionHandlerFilter();
    }

}
