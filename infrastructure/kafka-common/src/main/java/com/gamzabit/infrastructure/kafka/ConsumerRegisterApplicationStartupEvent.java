package com.gamzabit.infrastructure.kafka;

import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpoint;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Conditional(UseConsumerCondition.class)
public class ConsumerRegisterApplicationStartupEvent {

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    private final KafkaListenerContainerFactory<?> kafkaListenerContainerFactory;
    private final KafkaConsumerRegistrar kafkaConsumerRegistrar;
    private final KafkaListenerFactory kafkaListenerFactory;

    @EventListener(ApplicationReadyEvent.class)
    public void registerConsumers() {
        KafkaConsumerBuilder consumerBuilder = new KafkaConsumerBuilder();
        kafkaConsumerRegistrar.register(consumerBuilder);
        List<KafkaConsumer> consumers = consumerBuilder.build();
        List<KafkaListenerEndpoint> kafkaListenerEndpoints = consumers.stream()
            .map(kafkaListenerFactory::createKafkaListenerEndpoint)
            .toList();

        kafkaListenerEndpoints.forEach(listenerEndpoint -> {
            kafkaListenerEndpointRegistry.registerListenerContainer(
                listenerEndpoint, kafkaListenerContainerFactory);
        });
        kafkaListenerEndpointRegistry.start();
    }
}
