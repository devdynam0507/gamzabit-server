package com.gamzabit.api.order.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.gamzabit.api.order.service.dto.OrderCancelMessage;
import com.gamzabit.infrastructure.kafka.KafkaProducer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCancelProducer {

    private final static String CANCEL_ORDER_TOPIC = "cancel_order";

    private final KafkaProducer kafkaProducer;

    public void send(OrderCancelMessage message) {
        try {
            kafkaProducer.convertAndSend(CANCEL_ORDER_TOPIC, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
