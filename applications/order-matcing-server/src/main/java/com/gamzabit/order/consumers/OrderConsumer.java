package com.gamzabit.order.consumers;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamzabit.infrastructure.kafka.KafkaMessageListener;
import com.gamzabit.order.consumers.dto.OrderProduceMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderConsumer implements KafkaMessageListener<String> {

    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(String orderJson) {
        try {
            OrderProduceMessage orderProduceMessage = objectMapper.readValue(orderJson, OrderProduceMessage.class);
            // TODO: implements order validation
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
