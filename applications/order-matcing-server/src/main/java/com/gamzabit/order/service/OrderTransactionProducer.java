package com.gamzabit.order.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.gamzabit.infrastructure.kafka.KafkaProducer;

import com.gamzabit.order.service.dto.OrderTransactionMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderTransactionProducer {

    private final static String CONCLUDED_ORDER_TOPIC = "concluded_order";

    private final KafkaProducer kafkaProducer;

    public void send(OrderTransactionMessage message) {
        try {
            kafkaProducer.convertAndSend(CONCLUDED_ORDER_TOPIC, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
