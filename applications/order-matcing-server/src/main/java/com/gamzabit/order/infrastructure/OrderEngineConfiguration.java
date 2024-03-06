package com.gamzabit.order.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gamzabit.domain.redis.orderbook.OrderBookProcessor;
import com.gamzabit.engine.DataSourceOrderEngine;
import com.gamzabit.order.core.OrderEngineIncomingMessage;
import com.gamzabit.order.core.RedisDataSourceOrderEngine;
import com.gamzabit.order.core.RedisOrderPostProcessor;
import com.gamzabit.order.core.RedisOrderProcessorAdapter;
import com.gamzabit.order.service.OrderBookOperationReader;
import com.gamzabit.order.service.OrderTransactionProducer;

@Configuration
public class OrderEngineConfiguration {

    @Bean
    public DataSourceOrderEngine<OrderEngineIncomingMessage> dataSourceOrderEngine(
        OrderBookProcessor orderBookProcessor,
        OrderTransactionProducer orderTransactionProducer,
        OrderBookOperationReader orderBookOperationReader
    ) {
        RedisOrderProcessorAdapter redisOrderProcessorAdapter = new RedisOrderProcessorAdapter(orderBookProcessor);
        RedisOrderPostProcessor redisOrderPostProcessor = new RedisOrderPostProcessor(redisOrderProcessorAdapter);

        return new RedisDataSourceOrderEngine(
            redisOrderProcessorAdapter,
            redisOrderPostProcessor,
            orderTransactionProducer,
            orderBookOperationReader
        );
    }
}
