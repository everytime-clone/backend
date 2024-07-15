package com.study.everytime.global.security.filter;

import com.study.everytime.global.auth.exception.AuthException;
import com.study.everytime.global.auth.util.JwtUtils;
import com.study.everytime.global.security.token.JwtAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String PREFIX = "Bearer ";
    private static final String ROLE_USER = "ROLE_USER";

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/auth/**").matches(request)) {
            log.info("URI={}, JwtFilter 우회", request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }

        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (auth == null || !auth.startsWith(PREFIX)) {
            log.error("Authorization header 형식이 올바르지 않습니다.");
            throw new AuthException.InvalidTokenException();
        }

        String token = auth.split(" ")[1];
        if (token == null) {
            log.error("토큰 형식이 유효하지 않습니다.");
            throw new AuthException.InvalidTokenException();
        }

        Long userId = jwtUtils.parseAccessToken(token);
        Authentication authentication =
                new JwtAuthenticationToken(userId, token, Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER)));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("인증 완료");
        filterChain.doFilter(request, response);
    }

}
