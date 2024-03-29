package com.gamzabit.order.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gamzabit.infrastructure.kafka.KafkaConsumerRegistrar;
import com.gamzabit.order.consumers.OrderCancelConsumer;
import com.gamzabit.order.consumers.OrderConsumer;

@Configuration
public class ConsumerConfiguration {

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }

    @Bean
    KafkaConsumerRegistrar registrar(OrderConsumer orderConsumer, OrderCancelConsumer orderCancelConsumer) {
        return consumerBuilder -> {
            consumerBuilder.listener("created_order", "group_1", orderConsumer, String.class);
            consumerBuilder.listener("cancel_order", "group_1", orderCancelConsumer, String.class);
        };
    }
}
