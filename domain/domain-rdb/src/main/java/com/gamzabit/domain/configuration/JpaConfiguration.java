package com.gamzabit.domain.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@ComponentScan("com.gamzabit.domain")
@EnableJpaRepositories("com.gamzabit.domain")
public class JpaConfiguration {
}
