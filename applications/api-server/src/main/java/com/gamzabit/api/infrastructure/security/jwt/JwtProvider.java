package com.gamzabit.api.infrastructure.security.jwt;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.gamzabit.api.utils.Pair;

public class JwtProvider {

    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;
    private final JwtTokenType tokenType;

    public JwtProvider(String tokenSecret, JwtTokenType tokenType) {
        requireNonNull(tokenSecret, "tokenSecret");

        this.algorithm = Algorithm.HMAC256(tokenSecret);
        this.jwtVerifier = JWT.require(algorithm).build();
        this.tokenType = tokenType;
    }

    public <T> String encryptWithMultipleClaims(
        final List<Pair<String, String>> claims, final Long expiredSecond
    ) {
        JWTCreator.Builder jwtBuilder = JWT.create()
            .withExpiresAt(getExpiredDate(expiredSecond));
        for (final Pair<String, String> pair : claims) {
            jwtBuilder = jwtBuilder.withClaim(pair.left(), pair.right());
        }
        return jwtBuilder.sign(algorithm);
    }

    public <T> String encrypt(final String claimKey, final T value, final Long expiredSecond) {
        if (value instanceof Map<?,?> || value instanceof List) {
            throw new IllegalArgumentException("지원되지 않는 claim type 입니다.");
        }
        return JWT.create()
                  .withClaim(claimKey, String.valueOf(value))
                  .withExpiresAt(getExpiredDate(expiredSecond))
                  .sign(algorithm);
    }

    public <T> String encryptWithoutAlgorithm(
        final String claimKey, final T value, final Long expiredSecond) {
        if (value instanceof List || value instanceof Map) {
            throw new IllegalArgumentException("지원되지 않는 claim type 입니다.");
        }
        return JWT.create()
                  .withClaim(claimKey, String.valueOf(value))
                  .withExpiresAt(getExpiredDate(expiredSecond))
                  .sign(Algorithm.none());
    }

    public <T, V> String encryptMapWithoutAlgorithm(
        final Map<String, V> claims, final Long expiredSecond) {
        final JWTCreator.Builder builder = JWT.create();
        for (Map.Entry<String, V> entry : claims.entrySet()) {
            builder.withClaim(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return builder.withExpiresAt(getExpiredDate(expiredSecond))
                      .sign(Algorithm.none());
    }

    public <T> T decrypt(final String token, final String claimKey, final Class<? extends T > type) {
        if (Collection.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type)) {
            throw new IllegalArgumentException("지원되지 않는 claim type 입니다.");
        }
        return (T) jwtVerifier.verify(token)
                              .getClaim(claimKey)
                              .as(type);
    }

    public Map<String, Claim> decryptAllClaims(final String token) {
        return jwtVerifier.verify(token).getClaims();
    }

    public Map<String, Claim> decryptWithNoAlgorithm(final String token) {
        return JWT.decode(token)
                  .getClaims();
    }

    private Date getExpiredDate(final long expiredSecond) {
        final Date now = new Date();
        final Date expired = new Date();
        expired.setTime(now.getTime() + expiredSecond);
        return expired;
    }

    public JwtTokenType tokenType() {
        return tokenType;
    }
}
