package com.gamzabit.infrastructure.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@Conditional(UseProducerCondition.class)
public class KafkaProducerConfiguration {

    @Bean
    public KafkaProducer kafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return new KafkaProducer(objectMapper, kafkaTemplate);
    }
}
