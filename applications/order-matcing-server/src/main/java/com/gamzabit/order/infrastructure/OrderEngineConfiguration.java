package com.gamzabit.order.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gamzabit.domain.redis.orderbook.OrderBookProcessor;
import com.gamzabit.order.core.DataSourceOrderEngine;
import com.gamzabit.order.core.RedisDataSourceOrderEngine;
import com.gamzabit.order.core.RedisOrderProcessorAdapter;

@Configuration
public class OrderEngineConfiguration {

    @Bean
    public DataSourceOrderEngine dataSourceOrderEngine(
        OrderBookProcessor orderBookProcessor
    ) {
        RedisOrderProcessorAdapter redisOrderProcessorAdapter = new RedisOrderProcessorAdapter(orderBookProcessor);

        return new RedisDataSourceOrderEngine(redisOrderProcessorAdapter);
    }
}
