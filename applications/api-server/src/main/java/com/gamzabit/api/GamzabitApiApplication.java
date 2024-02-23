package com.gamzabit.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.gamzabit")
public class GamzabitApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamzabitApiApplication.class, args);
    }
}
