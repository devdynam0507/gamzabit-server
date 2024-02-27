package com.gamzabit.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gamzabit")
public class OrderMatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderMatchApplication.class, args);
    }
}
