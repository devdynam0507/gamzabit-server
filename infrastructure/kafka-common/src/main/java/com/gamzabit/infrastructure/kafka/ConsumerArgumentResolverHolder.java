package com.gamzabit.infrastructure.kafka;

import java.util.List;

import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;

public record ConsumerArgumentResolverHolder(List<HandlerMethodArgumentResolver> argumentResolvers) {}
