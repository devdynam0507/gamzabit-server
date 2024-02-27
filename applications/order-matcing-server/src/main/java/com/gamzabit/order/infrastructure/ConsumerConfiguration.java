package com.gamzabit.order.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gamzabit.infrastructure.kafka.KafkaConsumerRegistrar;
import com.gamzabit.order.OrderConsumer;

@Configuration
public class ConsumerConfiguration {

    @Bean
    KafkaConsumerRegistrar registrar() {
        return consumerBuilder -> {
            OrderConsumer consumer = new OrderConsumer();
            consumerBuilder.listener("created_order", "group_1", consumer, String.class);
        };
    }
}
