package com.gamzabit.api.infrastructure.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gamzabit.api.order.consumer.OrderTransactionConsumer;
import com.gamzabit.infrastructure.kafka.KafkaConsumerRegistrar;

@Configuration
public class ConsumerConfiguration {

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }

    @Bean
    KafkaConsumerRegistrar registrar(OrderTransactionConsumer orderTransactionConsumer) {
        return consumerBuilder -> {
            consumerBuilder.listener("concluded_order", "group_1", orderTransactionConsumer, String.class);
        };
    }
}
