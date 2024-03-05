package com.gamzabit.domain.redis.orderbook;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.gamzabit.domain.redis.RedisConfiguration;

@SpringBootTest(classes = { TestRedisConfiguration.class, RedisConfiguration.class})
class OrderBookRedisRepositoryImplTest {

    @Autowired
    private RedisTemplate<String, OrderBook> redisTemplate;

    private OrderBookRedisRepository orderBookRedisRepository;

    @BeforeEach
    void before() {
        orderBookRedisRepository = new OrderBookRedisRepositoryImpl(redisTemplate);
    }

    @Test
    void 오더북_아이디로_가져오기() {
        List<OrderBook> byId = orderBookRedisRepository.findAllById(List.of(109L, 110L));
        Object order_book = redisTemplate.opsForHash().get("order_book", 109L);

        System.out.println(order_book);
        System.out.println(byId);
    }
}