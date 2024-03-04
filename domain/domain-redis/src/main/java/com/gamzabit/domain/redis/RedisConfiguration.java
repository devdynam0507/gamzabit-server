package com.gamzabit.domain.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.gamzabit.domain.redis.orderbook.OrderBook;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, OrderBook> orderBookRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, OrderBook> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(OrderBook.class));
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    @Primary
    public StringRedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        template.setEnableTransactionSupport(true);

        return template;
    }
}
