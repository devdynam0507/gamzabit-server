package com.gamzabit.api.infrastructure.security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {

    @Bean
    JwtProvider accessTokenProvider(JwtSecretProperty jwtSecretProperty) {
        return new JwtProvider(jwtSecretProperty.getAccessSecret(), JwtTokenType.Access);
    }

    @Bean
    JwtProvider refreshTokenProvider(JwtSecretProperty jwtSecretProperty) {
        return new JwtProvider(jwtSecretProperty.getRefreshSecret(), JwtTokenType.Refresh);
    }
}
