package com.gamzabit.api.infrastructure.security.jwt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class JwtStrategy {

    private final Map<JwtTokenType, JwtProvider> providers = new HashMap<>();

    public JwtStrategy(Set<JwtProvider> jwtProviders) {
        for (JwtProvider jwtProvider : jwtProviders) {
            providers.put(jwtProvider.tokenType(), jwtProvider);
        }
    }

    public JwtProvider getProvider(JwtTokenType tokenType) {
        return providers.get(tokenType);
    }
}
