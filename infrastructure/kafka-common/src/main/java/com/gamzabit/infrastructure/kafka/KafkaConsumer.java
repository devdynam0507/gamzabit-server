package com.gamzabit.infrastructure.kafka;

public record KafkaConsumer(
    String topic,
    String groupId,
    KafkaMessageListener<?> listener,
    Class<?> parameterType
) {
}
