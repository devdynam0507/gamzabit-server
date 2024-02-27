package com.gamzabit.api.infrastructure.kafka;

import java.io.IOException;

import org.springframework.kafka.core.KafkaTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KafkaProducer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void convertAndSend(String topic, Object obj) throws IOException {
        String json;
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw e;
        }
        send(topic, json);
    }
}
