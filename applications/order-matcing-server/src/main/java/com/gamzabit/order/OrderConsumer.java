package com.gamzabit.order;

import com.gamzabit.infrastructure.kafka.KafkaMessageListener;

public class OrderConsumer implements KafkaMessageListener<String> {

    @Override
    public void onMessage(String orderJson) {
        System.out.println(orderJson);
    }
}
