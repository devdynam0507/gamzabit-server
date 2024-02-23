package com.gamzabit.api.infrastructure.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("jwt.token")
public class JwtSecretProperty {

    private String accessSecret;
    private String refreshSecret;
}
