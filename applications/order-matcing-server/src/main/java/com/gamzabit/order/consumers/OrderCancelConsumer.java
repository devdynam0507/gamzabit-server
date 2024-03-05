package com.gamzabit.order.consumers;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamzabit.infrastructure.kafka.KafkaMessageListener;
import com.gamzabit.order.consumers.dto.OrderCancelMessage;
import com.gamzabit.order.service.OrderCancelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderCancelConsumer implements KafkaMessageListener<String> {

    private final ObjectMapper objectMapper;
    private final OrderCancelService orderCancelService;

    @Override
    public void onMessage(String data) {
        try {
            OrderCancelMessage orderCancelMessage = objectMapper.readValue(data, OrderCancelMessage.class);

            orderCancelService.cancelOrder(orderCancelMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
