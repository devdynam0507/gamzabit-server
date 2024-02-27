package com.gamzabit.order;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @KafkaListener(topics = "order", groupId = "group_1")
    public void listen(String orderJson) {
        System.out.println(orderJson);
    }
}
