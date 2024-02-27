package com.gamzabit.infrastructure.kafka;

import java.util.ArrayList;
import java.util.List;

public class KafkaConsumerBuilder {

    private final List<KafkaConsumer> kafkaConsumers = new ArrayList<>();

    public <T> KafkaConsumerBuilder listener(
        String topic,
        String groupId,
        KafkaMessageListener<T> listener,
        Class<T> parameterType
    ) {
        kafkaConsumers.add(new KafkaConsumer(topic, groupId, listener, parameterType));
        return this;
    }

    public List<KafkaConsumer> build() {
        return kafkaConsumers;
    }
}
