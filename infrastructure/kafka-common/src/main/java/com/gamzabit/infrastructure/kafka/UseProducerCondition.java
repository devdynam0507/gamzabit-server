package com.gamzabit.infrastructure.kafka;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class UseProducerCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String kafkaProducerEnableProperty = context.getEnvironment().getProperty("spring.kafka.producer.enabled");
        return kafkaProducerEnableProperty != null && kafkaProducerEnableProperty.equals("true");
    }
}
