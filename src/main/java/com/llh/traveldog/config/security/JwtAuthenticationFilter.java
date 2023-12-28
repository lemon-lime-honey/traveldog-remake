package com.llh.traveldog.config.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private JwtTokenPrivider jwtTokenPrivider;

    public JwtAuthenticationFilter(JwtTokenPrivider jwtTokenPrivider) {
        this.jwtTokenPrivider = jwtTokenPrivider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenPrivider.resolveToken(servletRequest);
        LOGGER.info("[doFilterInternal] token 값 추출 완료. token: {}", token);

        LOGGER.info("[doFilterInternal] token 값 유효성 체크 시작");
        if (token != null && jwtTokenPrivider.validateToken(token)) {
            Authentication authentication = jwtTokenPrivider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LOGGER.info("[doFilterInternal] token 값 유효성 체크 완료");
        }

         filterChain.doFilter(servletRequest, servletResponse);
    }
}
