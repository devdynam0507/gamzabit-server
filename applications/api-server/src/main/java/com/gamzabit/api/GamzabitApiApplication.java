package com.gamzabit.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.gamzabit")
@ComponentScan("com.gamzabit")
public class GamzabitApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamzabitApiApplication.class, args);
    }
}
