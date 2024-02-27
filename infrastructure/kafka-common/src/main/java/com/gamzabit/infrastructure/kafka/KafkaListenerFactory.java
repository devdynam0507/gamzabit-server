package com.gamzabit.infrastructure.kafka;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.kafka.config.KafkaListenerEndpoint;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KafkaListenerFactory {

    AtomicLong endpointIdIndex = new AtomicLong(1);
    private final DefaultMessageHandlerMethodFactory messageHandlerMethodFactory;

    public KafkaListenerEndpoint createKafkaListenerEndpoint(KafkaConsumer consumer) {
        MethodKafkaListenerEndpoint<String, String> kafkaListenerEndpoint =
            createDefaultMethodKafkaListenerEndpoint(consumer.topic(), consumer.groupId());
        kafkaListenerEndpoint.setBean(consumer.listener());
        try {
            kafkaListenerEndpoint.setMethod(
                consumer.listener().getClass().getMethod("onMessage", String.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Attempt to call a non-existent method " + e);
        }

        return kafkaListenerEndpoint;
    }


    private MethodKafkaListenerEndpoint<String, String> createDefaultMethodKafkaListenerEndpoint(
        String topic,
        String groupId
    ) {
        MethodKafkaListenerEndpoint<String, String> kafkaListenerEndpoint = new MethodKafkaListenerEndpoint<>();
        kafkaListenerEndpoint.setId("kafkaListenerId-" + endpointIdIndex.incrementAndGet());
        kafkaListenerEndpoint.setGroupId(groupId);
        kafkaListenerEndpoint.setAutoStartup(true);
        kafkaListenerEndpoint.setTopics(topic);
        kafkaListenerEndpoint.setClientIdPrefix("consumers");
        kafkaListenerEndpoint.setMessageHandlerMethodFactory(messageHandlerMethodFactory);

        return kafkaListenerEndpoint;
    }
}
