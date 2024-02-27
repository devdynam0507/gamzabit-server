package com.gamzabit.infrastructure.kafka;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class UseConsumerCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String kafkaConsumerEnableProperty = context.getEnvironment().getProperty("spring.kafka.consumer.enabled");
        return kafkaConsumerEnableProperty != null && kafkaConsumerEnableProperty.equals("true");
    }
}
