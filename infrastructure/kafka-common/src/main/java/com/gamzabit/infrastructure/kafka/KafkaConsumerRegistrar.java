package com.gamzabit.infrastructure.kafka;

public interface KafkaConsumerRegistrar {

    void register(KafkaConsumerBuilder consumerBuilder);
}
