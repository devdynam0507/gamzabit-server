package com.gamzabit.infrastructure.kafka;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;

@Configuration
@Conditional(UseConsumerCondition.class)
public class KafkaConsumerConfiguration {

    @Bean
    ConsumerArgumentResolverHolder argumentResolverHolder() {
        PayloadMethodArgumentResolver payloadMethodArgumentResolver =
            new PayloadMethodArgumentResolver(new StringMessageConverter());

        return new ConsumerArgumentResolverHolder(List.of(payloadMethodArgumentResolver));
    }

    @Bean
    KafkaListenerFactory kafkaListenerFactory(
        ConsumerArgumentResolverHolder argumentResolverHolder) {
        DefaultMessageHandlerMethodFactory defaultMessageHandlerMethodFactory =
            new DefaultMessageHandlerMethodFactory();
        defaultMessageHandlerMethodFactory.setArgumentResolvers(argumentResolverHolder.argumentResolvers());

        return new KafkaListenerFactory(defaultMessageHandlerMethodFactory);
    }
}
