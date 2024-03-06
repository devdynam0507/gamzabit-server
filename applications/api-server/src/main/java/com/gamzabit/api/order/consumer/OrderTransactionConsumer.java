package com.gamzabit.api.order.consumer;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamzabit.api.order.consumer.dto.OrderTransactionMessage;
import com.gamzabit.api.order.service.OrderTransactionService;
import com.gamzabit.infrastructure.kafka.KafkaMessageListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderTransactionConsumer implements KafkaMessageListener<String> {

    private final ObjectMapper objectMapper;
    private final OrderTransactionService orderTransactionService;

    @Override
    public void onMessage(String data) {
        try {
            OrderTransactionMessage orderTransactionMessage =
                objectMapper.readValue(data, OrderTransactionMessage.class);

            log.info("incoming message concluded: {}", orderTransactionMessage);
            if (orderTransactionMessage.transactions().isEmpty()) {
                return;
            }
            orderTransactionService.handleTransaction(orderTransactionMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
