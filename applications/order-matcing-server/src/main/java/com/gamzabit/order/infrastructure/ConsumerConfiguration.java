package com.gamzabit.order.infrastructure;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamzabit.infrastructure.kafka.KafkaConsumerRegistrar;
import com.gamzabit.infrastructure.kafka.KafkaMessageListener;

@Configuration
public class ConsumerConfiguration {

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    KafkaConsumerRegistrar registrar(Set<KafkaMessageListener<String>> consumers) {
        return consumerBuilder -> {
            consumers.forEach(consumer -> {
                consumerBuilder.listener("created_order", "group_1", consumer, String.class);
            });
        };
    }
}
