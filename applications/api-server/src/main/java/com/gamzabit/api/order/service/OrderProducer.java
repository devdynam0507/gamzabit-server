package com.gamzabit.api.order.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.gamzabit.api.order.service.dto.OrderProduceMessage;
import com.gamzabit.infrastructure.kafka.KafkaProducer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final static String CREATED_ORDER_TOPIC = "created_order";

    private final KafkaProducer kafkaProducer;

    public void send(OrderProduceMessage message) {
        try {
            kafkaProducer.convertAndSend(CREATED_ORDER_TOPIC, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
