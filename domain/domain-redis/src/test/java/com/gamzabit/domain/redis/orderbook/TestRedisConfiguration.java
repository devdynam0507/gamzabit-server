package com.gamzabit.domain.redis.orderbook;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

@TestConfiguration
public class TestRedisConfiguration {

    @Bean
    RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
