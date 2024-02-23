package com.gamzabit.api.infrastructure.security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {

    @Bean
    JwtProvider accessTokenProvider() {
        return new JwtProvider( /* TODO: future remove */ "xxx-token-secret", JwtTokenType.Access);
    }

    @Bean
    JwtProvider refreshTokenProvider() {
        return new JwtProvider( /* TODO: future remove */ "xxx-token-secret", JwtTokenType.Refresh);
    }
}
