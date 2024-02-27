package com.gamzabit.infrastructure.kafka;

@FunctionalInterface
public interface KafkaMessageListener<T> {

    void onMessage(T data);
}
